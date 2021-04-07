package com.spiegelberger.springit.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

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
	private final MailService mailService;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, 
							RoleService roleService, MailService mailService) {
		this.userRepository = userRepository;
		this.encoder =  encoder;		
		this.roleService  = roleService;
		this.mailService = mailService;
	}
	
	public User register(User user) {
		
		//take the password from the form and encode it
		String secret = /*"{bcrypt}" + */encoder.encode(user.getPassword());
		user.setPassword(secret);
		
		//confirm password		
		user.setConfirmPassword(secret);
		
		//assign a role to the user
		user.addRole(roleService.findByName("ROLE_USER"));
		
		//set an activation code
		user.setActivationCode(UUID.randomUUID().toString());
		
		//disable user
		user.setEnabled(false);
		
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
	
	@Transactional
    public void saveUsers(User... users) {
        for(User user : users) {
            log.info("Saving User: " + user.getEmail());
            userRepository.save(user);
        }
    }
	
	public void sendActivationEmail(User user) {
		mailService.sendActivationEmail(user);
	}
	
	public void sendWelcomeEmail(User user) {
	    mailService.sendWelcomeEmail(user);
	}
	
	public Optional<User>findByEmailAndActivationCode(String email, String activationCode){
		return userRepository.findByEmailAndActivationCode(email, activationCode);
	}
	
	public Optional<User>findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
