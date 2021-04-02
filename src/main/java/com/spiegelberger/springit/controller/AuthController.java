package com.spiegelberger.springit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.spiegelberger.springit.service.UserService;

@Controller
public class AuthController {

	private UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/profile")
	public String profile() {
	    return "auth/profile";
	}

	@GetMapping("/register")
	public String register() {
	    return "auth/register";
	}
	
}
