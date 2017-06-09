package com.danchu.momuck.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import com.danchu.momuck.dao.FoodDao;
import com.danchu.momuck.vo.Food;
import com.mysql.jdbc.log.Log;


/**
 * FoodMapper
 * 
 * @author lhbv1
 */
@Repository
public class FoodMapper implements FoodDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private static final String NAMESPACE = "Food";
	private static final Logger LOG = LoggerFactory.getLogger(AccountMapper.class);

	public Food insertFood(Food food) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", food.getName());
		map.put("restaurantSeq", food.getRestaurantSeq());

		Food check = sqlSessionTemplate.selectOne(NAMESPACE + ".getFoodByNameAndRestaurantSeq", map);
		if (check == null) {
			sqlSessionTemplate.insert(NAMESPACE + ".insertFood", food);
		} else return null;
		
		return food;
	}

	public List<Food> selectFoodList(int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("offset", 0 + 10 * (page - 1));
		return sqlSessionTemplate.selectList(NAMESPACE + ".getAllFoodList", map);
	}

	public List<Food> selectFoodListByCategory(String category, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("offset", 0 + 10 * (page - 1));
		map.put("category", category);
		return sqlSessionTemplate.selectList(NAMESPACE + ".getCategoryFoodList", map);
	}

	public int deleteFood(String name, int restaurantSeq) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("restaurantSeq", restaurantSeq);
		sqlSessionTemplate.delete(NAMESPACE + ".deleteFood", map);
		return 0;
	}

	public List<Food> selectFoodListByKeyword(String keyword, int page, int sort) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("offset", 0 + 10 * (page - 1));
		map.put("keyword", keyword);
		map.put("sort", sort);

		LOG.info(keyword);
		LOG.info(String.valueOf(page));
		LOG.info(String.valueOf(sort));
		return sqlSessionTemplate.selectList(NAMESPACE + ".selectFoodListByKeyword", map);
	}
}
