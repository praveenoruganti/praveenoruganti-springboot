package com.praveen.restservices.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.praveen.restservices.model.User1;



@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class HelloControllerTest {

	MockMvc mockMvc;

	@Autowired
	private HelloController helloController;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.helloController).build();
	}

	@Test
	public void testGreetingUser() throws Exception {
	this.mockMvc.perform(get("/rest/hello/getMsg/user"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", is("praveen-user-management-service Welcome User To Praveen Oruganti Forum !!")));
	}

	@Test
	public void testGreetingMsgBean() throws Exception {
	this.mockMvc.perform(get("/rest/hello/getMsgBean/path-variable/Prasad"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", is("<User1><userId>149903</userId><userName>Prasad</userName>"
	            		+ "<userEmail>praveenorugantitech@gmail.com</userEmail><address>Hyderabad</address></User1>")));
	}

	@Test
	public void testGreetingBean() {
		User1 user1 = helloController.greetingBean();
		assertEquals(user1.getUserEmail(), "praveenorugantitech@gmail.com");
		assertEquals(user1.getAddress(), "Hyderabad");
		assertEquals(user1.getUserId(), Integer.valueOf(149903));
		assertEquals(user1.getUserName(), "PraveenOruganti");
	}

	@Test
	public void testGreeting() {
		String result = helloController.greeting();
		assertEquals(result, "praveen-user-management-service Welcome To Praveen Oruganti Forum !!");
	}

}
