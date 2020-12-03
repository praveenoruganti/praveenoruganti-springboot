package com.praveen.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/getMsg")
public class MessageController {

	@GetMapping("/admin")
	public String getAdminMessage() {
		return "Hello Admin";
	}
	
	@GetMapping("/user")
	public String getUserMessage() {
		return "Hello User";
	}
}
