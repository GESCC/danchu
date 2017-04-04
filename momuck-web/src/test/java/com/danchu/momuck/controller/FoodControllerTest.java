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

import com.danchu.momuck.dao.FoodDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/root-context.xml", "classpath:servlet-context.xml" })
public class FoodControllerTest {

	private static final String NAME = "봉골레 파스타";
	private static final int PRICE = 8000;
	private static final String CATEGORY = "파스타";
	private static final float AVG_SCORE = (float) 3.0;
	private static final String IMAGE_MAIN = "test_image.jpg";
	private static final String IMAGE_EXTRA = "test_thumnail.jpg";
	private static final int RESTAURANT_SEQ = 1;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void insertFood() throws Exception {
		String jsonParam = "{" + "\"name\" : \"" + NAME + "\"," + "\"price\" : "
				+ PRICE + "," + "\"avg_score\" : " + AVG_SCORE + "," + "\"category\" : \"" + CATEGORY + "\","
				+ "\"image_main\" : \"" + IMAGE_MAIN + "\"," + "\"image_extra\" : \"" + IMAGE_EXTRA + "\","
				+ "\"restaurant_seq\" : " + RESTAURANT_SEQ + "}";

		this.mockMvc.perform(post("/foods").contentType(MediaType.APPLICATION_JSON).content(jsonParam))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasKey("code"))).andExpect(jsonPath("$.code").value("200")).andReturn();
	}

	@Test
	public void getFoodList() throws Exception {

		this.mockMvc.perform(get("/foods").contentType(MediaType.APPLICATION_JSON).param("page", "1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasKey("code")))
				.andExpect(jsonPath("$.code").value("200"))
				.andReturn();
	}
}
