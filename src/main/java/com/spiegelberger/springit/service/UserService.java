package com.spiegelberger.springit.service;

import org.springframework.stereotype.Service;

import com.spiegelberger.springit.domain.User;
import com.spiegelberger.springit.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User register(User user) {
		return user;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	

}
