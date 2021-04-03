package com.spiegelberger.springit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spiegelberger.springit.domain.User;
import com.spiegelberger.springit.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	private final RoleService roleService;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, 
													RoleService roleService) {
		this.userRepository = userRepository;
		this.encoder =  encoder;
		this.roleService  = roleService;
	}
	
	public User register(User user) {
		
		//take the password from the form and encode it
		String secret = "{bcrypt}" + encoder.encode(user.getPassword());
		user.setPassword(secret);
		
		//confirm password		
		//assign a role to the user
		user.addRole(roleService.findByName("ROLE_USER"));
		
		//set an activation code
		
		//disable user
		
		//save user
		save(user);
		
		//send activation email
		sendActivationEmail(user);
		
		//return user
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void sendActivationEmail(User user) {
		//added later
	}
}
