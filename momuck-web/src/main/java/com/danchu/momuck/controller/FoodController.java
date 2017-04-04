package com.danchu.momuck.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danchu.momuck.service.FoodService;
import com.danchu.momuck.view.ResultView;
import com.danchu.momuck.vo.Food;
import com.danchu.momuck.vo.Restaurant;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * FoodController
 * 
 * @author lhbv1
 */
@Controller
@RequestMapping(value = "/foods")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResultView register(HttpServletRequest req, HttpServletResponse res, @Valid @RequestBody Food food, BindingResult bindingResult) {
		//validation 실패
		if(bindingResult.hasErrors()) {
			return new ResultView("500", "Register fail" + bindingResult.getFieldError().getDefaultMessage(), null);
		}
		
		//duplicated name exception
		Food result = foodService.createFood(food);
		if (result == null) {
			return new ResultView("500", "Duplicated name", null);
		}

		return new ResultView("200", "Register success" , food);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResultView list(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "category", required = false) String category, 
			@RequestParam(value = "page") int page) throws JsonProcessingException {
		List<Food> result = foodService.getFoodList(category, page);
		
		//page validation
		if (page<=0) {
			return new ResultView("500", "Wrong page number", null);
		}
		
		//data check
		if (result == null) {
			return new ResultView("500", "No data", null);
		}
		return new ResultView("200", "Food list success", result);
	}
	
	/**
	 * 
	 * @TODO 매장 주인이랑 관리자만 지울수 있도록 로직 추가해야됨
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
	public ResultView delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int foodSeq) { 
		int result = foodService.deleteFood(foodSeq);
		if(result < 0) {
			return new ResultView("500", "Delete failed", null);
		}
		return new ResultView("200", "Delete success", null);
	}
}
