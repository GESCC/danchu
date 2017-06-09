package com.danchu.momuck.service;

import java.util.List;

import com.danchu.momuck.vo.Food;

public interface SearchService {

	List<Food> search(String keyword, int page, int sort);
}
