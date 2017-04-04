package com.danchu.momuck.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danchu.momuck.service.RestaurantService;
import com.danchu.momuck.view.ResultView;
import com.danchu.momuck.vo.Restaurant;

/**
 * RestaurantController 
 *
 * @author lhbv1
 */
@Controller
@RequestMapping(value = "/restaurants")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResultView register(HttpServletRequest req, HttpServletResponse res, @Valid @RequestBody Restaurant restaurant, BindingResult bindingResult) {
		//validation 실패
		if(bindingResult.hasErrors()) {
			return new ResultView("500", "Register fail" + bindingResult.getFieldError().getDefaultMessage(), null);
		}
		
		//duplicated name exception
		Restaurant result = restaurantService.createRestaurant(restaurant);
		if (result == null) {
			return new ResultView("500", "Duplicated name", null);
		}
		return new ResultView("200", "Register success", restaurant);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET, consumes = "application/json")
	public ResultView select(HttpServletRequest req, HttpServletResponse res, @PathVariable("restaurant_id") int restaurantId) {
		Restaurant result = restaurantService.selectRestaurant(restaurantId);
		if(result == null) {
			return new ResultView("500", "No data", null);
		}
		return new ResultView("200", "Success", result);
	}
	
	/**
	 * 
	 * @TODO 매장 주인이랑 관리자만 지울수 있도록 로직 추가해야됨
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
	public ResultView delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int restaurantSeq) { 
		int result = restaurantService.deleteRestaurant(restaurantSeq);
		if(result < 0) {
			return new ResultView("500", "Delete failed", null);
		}
		return new ResultView("200", "Delete success", null);
	}
}
