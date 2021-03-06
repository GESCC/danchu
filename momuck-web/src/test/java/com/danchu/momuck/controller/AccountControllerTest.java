package com.danchu.momuck.controller;

import com.danchu.momuck.dao.AccountDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * AccountControllerTest
 *
 * @author geine
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerTest {

    private static final String EMAIL = "lalala3423@hanmail.net";
    private static final String PASSWORD = "test";
    private static final String NAME = "jjeongg";
    private static final String PROFIME_IMAGE = "test.jpg";
    private static final String ACCOUT_TYPE = "1";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private AccountDao accountDao;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void insertAndLoginAccount() throws Exception {
    	
    	accountDao.deleteAccount(EMAIL);
    	
        String jsonParm = "{" +
                "\"email\" : \"" + EMAIL + "\"," +
                "\"name\" : \"" + NAME + "\"," +
                "\"password\" : \"" + PASSWORD + "\"," +
                "\"profile_image\" : \"" + PROFIME_IMAGE + "\"," +
                "\"account_type\" : \"" + ACCOUT_TYPE + "\"" +
                "}";

        this.mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasKey("code")))
                .andExpect(jsonPath("$.code").value("200"))
                .andReturn();

        jsonParm = "{" +
                "\"email\" : \"" + EMAIL + "\"," +
                "\"name\" : \"" + NAME + "\"," +
                "\"password\" : \"" + PASSWORD + "\"" +
                "}";

        this.mockMvc.perform(post("/accounts/login").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasKey("code")))
                .andExpect(jsonPath("$.code").value("900"))
                .andReturn();

        jsonParm = "{" +
                "\"email\" : \"" + EMAIL + "1" + "\"," +
                "\"name\" : \"" + NAME + "\"," +
                "\"password\" : \"" + PASSWORD + "\"" +
                "}";

        this.mockMvc.perform(post("/accounts/login").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasKey("code")))
                .andExpect(jsonPath("$.code").value("901"))
                .andReturn();

        jsonParm = "{" +
                "\"email\" : \"" + EMAIL + "\"," +
                "\"name\" : \"" + NAME + "\"," +
                "\"password\" : \"" + PASSWORD + "1" + "\"" +
                "}";

        this.mockMvc.perform(post("/accounts/login").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasKey("code")))
                .andExpect(jsonPath("$.code").value("902"))
                .andReturn();
    }
    
    @Test
    public void resetPassword() throws Exception{
    	String jsonParm = "{\"email\" : \"" + EMAIL + "\"}";
    	
    	this.mockMvc.perform(post("/accounts/reset_password").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasKey("code")))
        .andExpect(jsonPath("$.code").value("200"))
        .andReturn();
    	
    	jsonParm = "{\"email\" : \"wrong_" + EMAIL + "\"}";
    	
    	this.mockMvc.perform(post("/accounts/reset_password").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasKey("code")))
        .andExpect(jsonPath("$.code").value("501"))
        .andReturn();
    }
}
