package com.danchu.momuck.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danchu.momuck.service.SearchService;
import com.danchu.momuck.view.ResultView;
import com.danchu.momuck.vo.Food;

@Controller
public class SearchController {

    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;
    
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResultView search(@RequestParam String keyword, @RequestParam int page, @RequestParam(defaultValue = "0") int sort) {
		LOG.info(keyword);
		LOG.info(String.valueOf(page));
		LOG.info(String.valueOf(sort));
    	List<Food> result = searchService.search(keyword, page, sort);
    	
    	
    	return new ResultView("200", "success", result);
    }
    
}
