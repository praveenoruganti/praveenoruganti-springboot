package com.praveen.restservices.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController1Test {

private MockMvc mockmvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om=new ObjectMapper();
	
	@Before
	public void setup() {
		mockmvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void createUserTest() throws Exception {
//		/*
//		 * User1 u=new User1(); u.setAddress("Hyderabad");
//		 * u.setUserEmail("op@gmail.com"); u.setUserId(149903);
//		 * u.setUserName("praveenoruganti");
//		 * System.out.println("in unit test case of post data"); String
//		 * jsonrequest=om.writeValueAsString(u); MvcResult
//		 * result=mockmvc.perform(post("/rest/adduser1") .content(jsonrequest)
//		 * .content(MediaType.APPLICATION_JSON_VALUE))
//		 * .andExpect(status().isOk()).andReturn();
//		 */
		
		
		
		
		//Assert.assertTrue(result.getResponse().getStatus()==HttpStatus.CREATED.ordinal());
	}
	
}
