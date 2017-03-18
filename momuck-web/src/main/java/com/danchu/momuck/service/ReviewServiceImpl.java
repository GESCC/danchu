package com.danchu.momuck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.danchu.momuck.dao.ReviewDao;
import com.danchu.momuck.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private ReviewDao reviewDao;

	public Review submitReview(Review review) {
		//get food_restaurant_seq
		int restSeq = reviewDao.getRestarantSeq(review.getFoodSeq());
		review.setFoodRestaurantSeq(restSeq);
		return reviewDao.submitReview(review);
	}

	public int updateReview(Review review) {
		try{
			reviewDao.updateReview(review);
		}catch (Exception e) {
			return  -1;
		}
		return 0;
	}

	public int deleteReview(int seq) {
		try{
			reviewDao.deleteReview(seq);
		}catch (Exception e) {
			return  -1;
		}
		return 0;
	}

	public List<Review> selectReviewList(int foodSeq) {
		return reviewDao.selectReviewList(foodSeq);
	}
	

}
