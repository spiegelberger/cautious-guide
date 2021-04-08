package com.spiegelberger.springit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiegelberger.springit.domain.User;
import com.spiegelberger.springit.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public String register(Model model) {
		
		model.addAttribute("user", new User());
		
	    return "auth/register";
	}
	
	@PostMapping("/register")
	public String registerNewUser(@Valid User user,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			//show validation errors
			log.info("Validation errors were found while registering a new user");
			
			model.addAttribute("user", user);
			model.addAttribute("validationErrors", bindingResult.getAllErrors());
			
			return "auth/register";
		}
		else {
			//Register new User
			User newUser = userService.register(user);
			
			redirectAttributes
				.addAttribute("id", newUser.getId())
				.addFlashAttribute("success", true);
			
			return "redirect:/register";
		}
		
	}
	
	@GetMapping("activate/{email}/{activationCode}")
	public String activate(@PathVariable String email, @PathVariable String activationCode) {
		
		Optional<User>user = userService.findByEmailAndActivationCode(email, activationCode);
			if(user.isPresent()) {
				User newUser = user.get();
				newUser.setEnabled(true);
				newUser.setConfirmPassword(newUser.getActivationCode());
				
				userService.save(newUser);
				userService.sendWelcomeEmail(newUser);
				
				return "auth/activated";
			}
			
			return "redirect:/";
	}
	
	
}
