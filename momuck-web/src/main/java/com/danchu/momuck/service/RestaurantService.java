package com.danchu.momuck.service;

import java.util.List;

import com.danchu.momuck.vo.Restaurant;

public interface RestaurantService {

	Restaurant createRestaurant(Restaurant restaurant);

	int updateRestaurant(Restaurant restaurant);

	Restaurant selectRestaurant(int seq);

	int deleteRestaurant(int seq);

}