package com.danchu.momuck.service;

import java.util.List;

import com.danchu.momuck.vo.Food;

/**
 * FoodService
 * 
 * @author lhbv1
 */
public interface FoodService {
	Food createFood(Food food);
	
	public List<Food> getFoodList(String category, int page);
	
	int deleteFood(int seq);
}
