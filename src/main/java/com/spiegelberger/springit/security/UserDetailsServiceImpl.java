package com.spiegelberger.springit.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spiegelberger.springit.domain.User;
import com.spiegelberger.springit.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	
	private UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User>user = userRepository.findByEmail(username);
			if(!user.isPresent()) {
				throw new UsernameNotFoundException(username);
			}
		return user.get();
	}

}
