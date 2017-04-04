package com.danchu.momuck.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danchu.momuck.dao.RestaurantDao;
import com.danchu.momuck.vo.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);
	
	@Autowired
	RestaurantDao restaurantDao;
	
	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantDao.insertRestaurant(restaurant);
	}
	
	public int updateRestaurant(Restaurant restaurant) {
		return restaurantDao.updateRestaurant(restaurant);
	}

	public Restaurant selectRestaurant(int seq) {
		return restaurantDao.selectRestaurant(seq);
	}

	public int deleteRestaurant(int seq) {
		return restaurantDao.deleteRestaurant(seq);
	}
}
