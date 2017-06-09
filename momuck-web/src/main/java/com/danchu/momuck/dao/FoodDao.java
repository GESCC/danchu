package com.danchu.momuck.dao;

import java.util.List;

import com.danchu.momuck.vo.Food;


/**
 * FoodDao
 * 
 * @author lhbv1
 */
public interface FoodDao {
	public Food insertFood(Food food);
	public List<Food> selectFoodList(int page);
	public List<Food> selectFoodListByCategory(String category, int page);
	public List<Food> selectFoodListByKeyword(String keyword, int page, int sort);
	public int deleteFood(String name, int restaurantSeq);
}
