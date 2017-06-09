package com.danchu.momuck.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danchu.momuck.dao.FoodDao;
import com.danchu.momuck.vo.Food;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private FoodDao foodDao;
    
	public List<Food> search(String keyword, int page, int sort) {
		return foodDao.selectFoodListByKeyword(keyword, page, sort);
	}

}
