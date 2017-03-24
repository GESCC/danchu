package com.danchu.momuck.service;

import com.danchu.momuck.dao.AccountDao;
import com.danchu.momuck.domain.LoginResult;
import com.danchu.momuck.util.AES256Util;
import com.danchu.momuck.vo.Account;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.net.URLCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AccountServiceImpl
 *
 * @author geine
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);
    private static final String AES256KEY = "aes-256-momuck-key";
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private AccountDao accountDao;

    public Account createAccount(Account account) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountDao.insertAccount(account);
    }

    public LoginResult login(Account account) {

        Account regAccount = accountDao.selectAccount(account.getEmail());

        if (regAccount == null) {
            return LoginResult.NOT_EXIST_EMAIL;
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);

        if (!bCryptPasswordEncoder.matches(account.getPassword(), regAccount.getPassword())) {
            return LoginResult.NOT_CORRECT_PASSWORD;
        }

        return LoginResult.SUCCESS;
    }

	public void sendEmail(Account account) {

		String normalString = account.getEmail() + ":" + account.getName();

		try {
			AES256Util aes256 = new AES256Util(AES256KEY);
			URLCodec codec = new URLCodec();
			String encString = codec.encode(aes256.aesEncode(normalString));

			String htmlMsg = "<h3>이메일 계정을 인증받으시려면 아래 링크를 클릭해주세요</h3>" 
					+ "<a href=\"https://dev.momuck.com/momuck/accounts/verify/"
					+ encString 
					+ "\">Verify Your Account!</a>";

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(htmlMsg, "text/html");
			helper.setTo(account.getEmail());
			helper.setSubject("MOMUCK 이메일 주소 인증");
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyAccount(String verifyKey) {
		

	}
}
