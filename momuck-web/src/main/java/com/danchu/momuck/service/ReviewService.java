package com.danchu.momuck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.danchu.momuck.vo.Review;

@Service
public interface ReviewService {
	
	Review submitReview(Review review);
	
	
}
