package com.praveen.ems.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.praveen.ems.web.model.User;
import com.praveen.ems.web.model.Login;
import com.praveen.ems.web.service.UserService;

@Controller
public class EMSLoginController {
	@Autowired
	public UserService userService;

	@GetMapping("/")
	public ModelAndView showEMSLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		return mav;
	}

	@PostMapping(value = "/loginProcess")
	public String login(@ModelAttribute("login") Login login, BindingResult bindingResult, ModelMap model) {
		
		User chkuser=userService.checkUser(login);
		boolean isValidUser = false;
		
		if(chkuser!=null) {
			User user = userService.validateUser(login);
			
			if (null != user && user.getUsername().equals(login.getUsername())
					&& user.getPassword().equals(login.getPassword())) {
				isValidUser = true;
				model.addAttribute("username", user.getUsername());
			}else {
				model.addAttribute("message", "Invalid credentials!!");
			}
		}else {
			model.addAttribute("message", "Employee not found please register!!");
		}
				
		return isValidUser ? "welcome" : "login";
	}
}
