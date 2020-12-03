package com.praveen.restservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.praveen.restservices.config.RestDataValueProperties;
import com.praveen.restservices.model.User1;

import lombok.extern.slf4j.Slf4j;


@RestController
@RefreshScope
@Slf4j
@RequestMapping("/rest/hello")
public class HelloController {
	
	@Autowired
	private RestDataValueProperties prop;
	
	
	@GetMapping("/getMsg")
	public String greeting() {
		log.info("HelloController.greeting() Start");
		String msg=prop.getName() + " Welcome To Praveen Oruganti Forum !!";
		log.info("HelloController.greeting() End");
		return msg;
	}
	
	@GetMapping("/getMsg/user")
	public String greetingUser() {
		return prop.getName() + " Welcome User To Praveen Oruganti Forum !!";
	}
	
	@GetMapping("/getMsg/admin")
	public String greetingAdmin() {
		return prop.getName() + " Welcome Admin To Praveen Oruganti Forum !!";
	}		
	
	
	@GetMapping("/getMsgBean")
	public User1 greetingBean() {		
		return new User1(149903,"PraveenOruganti","praveenoruganti@gmail.com","Hyderabad");
	}
	
	@GetMapping("/getMsgBean/path-variable/{name}")
	public User1 greetingBean(@PathVariable String name) {		
		return new User1(149903,name,"praveenoruganti@gmail.com","Hyderabad");
	}
	
	@PostMapping("/getMsgBean")
	public ResponseEntity<User1> greetingBean(@RequestBody User1 user1,UriComponentsBuilder builder) {		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/getMsgBean/{userName}").buildAndExpand(user1.getUserName()).toUri());
		return new ResponseEntity<User1>(headers, HttpStatus.CREATED);
	}

}
