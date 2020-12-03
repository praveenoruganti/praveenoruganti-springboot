package com.praveen.ems.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.praveen.ems.web.model.Login;
import com.praveen.ems.web.model.User;
import com.praveen.ems.web.service.UserService;

@Controller
public class EMSRegistrationController {
	@Autowired
	public UserService userService;

	@GetMapping("/register")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		return mav;
	}

	@PostMapping("/registerProcess")
	public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model) {
		Login login = Login.builder().username(user.getUsername()).password(user.getPassword()).build();
		boolean isExistingUser = false;
		try {
		User chkuser = userService.checkUser(login);
		if (chkuser != null) {
			isExistingUser = true;
			model.addAttribute("message", "User Already Exists!! Please Login..");
		} else {

			userService.addUser(user);
			model.addAttribute("username", user.getUsername());
		}
		}catch(ConstraintViolationException cve) {
			isExistingUser = true;
			model.addAttribute("message", "Please make sure you have provided the username and password !!");
		}

		return isExistingUser ? "register" : "welcome";
	}
}