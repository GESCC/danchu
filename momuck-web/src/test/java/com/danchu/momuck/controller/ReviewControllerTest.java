package com.danchu.momuck.controller;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.danchu.momuck.dao.ReviewDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/root-context.xml", "classpath:servlet-context.xml"})
public class ReviewControllerTest {
	
	Logger logger = Logger.getLogger(this.getClass());

	private int foods_id = 1;
	private int reviews_id;
	
	private float score;
	private String message;
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void submitReview() throws Exception {
		
		score = (float) 4.1;
		message = "submit test";
		
		String jsonParm = "{" +
	            "\"score\" : \"" + score + "\"," +
	            "\"message\" : \"" + message + "\"" +              
	            "}";
		
		this.mockMvc.perform(post("/foods/{foods_id}/reviews",foods_id)
				.contentType(MediaType.APPLICATION_JSON).content(jsonParm))
		.andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasKey("code")))
        .andExpect(jsonPath("$.code").value("200"))
        .andReturn();
	}
	
	@Test
	public void updateReview() throws Exception {
		
		reviews_id = 1;
		score = (float) 1.0;
		message = "update test";

		String jsonParm = "{" +
	            "\"score\" : \"" + score + "\"," +
	            "\"message\" : \"" + message + "\"" +              
	            "}";
		
		this.mockMvc.perform(put("/foods/{foods_id}/reviews/{reviews_id}", foods_id, reviews_id)
				.contentType(MediaType.APPLICATION_JSON).content(jsonParm))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasKey("code")))
        .andExpect(jsonPath("$.code").value("200"))
        .andReturn();
	}
	
	@Test
	public void deleteReview() throws Exception {
		
		reviews_id = 1;
		
		this.mockMvc.perform(delete("/foods/{foods_id}/reviews/{reviews_id}", foods_id, reviews_id))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasKey("code")))
        .andExpect(jsonPath("$.code").value("200"))
        .andReturn();
	}

	@Test
	public void selectReview() throws Exception {
		
		MvcResult result = this.mockMvc.perform(get("/foods/{foods_id}/reviews", foods_id))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$", hasKey("code")))
        .andExpect(jsonPath("$.code").value("200"))
        .andReturn();
		
		logger.info(result.getResponse().getContentAsString());
	}
}
