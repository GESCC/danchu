package com.danchu.momuck.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.danchu.momuck.vo.Review;

@Repository
public interface ReviewDao {
	
	
	public Review submitReview(Review review);
	public int getRestarantSeq(int foodSeq);
	public void updateReview(Review review);
	public void deleteReview(int seq);
	public List<Review> selectReviewList(int foodSeq);
	
}
