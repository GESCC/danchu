package com.danchu.momuck.controller;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.danchu.momuck.dao.AccountDao;
import com.danchu.momuck.dao.RestaurantDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/root-context.xml", "classpath:servlet-context.xml" })
public class RestaurantControllerTest {

	private static final String NAME = "리틀 파스타";
	private static final float AVG_SCORE = 0;
	private static final String LOCATION_COORD = "0,0";
	private static final String LOCATION_TEXT = "서울시 동작구";
	private static final String PHONE_NUMBER = "010-4444-4444";
	private static final String IMAGE_MAIN = "test.jpg";
	private static final String IMAGE_EXTRA = "test_extra.jpg";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private RestaurantDao restaurantDao;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void insertRestaurant() throws Exception {
		String jsonParm = "{" + "\"name\" : \"" + NAME + "\"," + "\"avg_score\" : " + AVG_SCORE + "," + "\"location_coord\" : \""
				+ LOCATION_COORD + "\"," + "\"location_text\" : \"" + LOCATION_TEXT + "\"," + "\"phone_number\" : \""
				+ PHONE_NUMBER + "\"," + "\"image_main\" : \"" + IMAGE_MAIN + "\","+ "\"image_extra\" : \"" + IMAGE_EXTRA + "\"}";

		this.mockMvc.perform(post("/restaurants").contentType(MediaType.APPLICATION_JSON).content(jsonParm))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasKey("code")))
				.andExpect(jsonPath("$.code").value("200"))
				.andReturn();
	}
	
	@Test
	public void selectRestaurant() throws Exception {
		this.mockMvc.perform(get("/restaurants/{restaurant_id}", 1).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andExpect(jsonPath("$", hasKey("code")))
			.andExpect(jsonPath("$.code").value("200"))
			.andReturn();
	}
}