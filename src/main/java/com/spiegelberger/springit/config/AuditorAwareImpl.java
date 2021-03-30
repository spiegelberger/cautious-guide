package com.spiegelberger.springit.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spiegelberger.springit.domain.User;


public class AuditorAwareImpl implements AuditorAware<String> {

	/*
	 * This method will allow us to get the username 
	 * (email in our case) of the currently logged in user.
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		
		if(SecurityContextHolder.getContext().getAuthentication() == null ) {
            return Optional.of("admin@gmail.com");
        } else {
            return Optional.of(((User) SecurityContextHolder
            		.getContext().getAuthentication().getPrincipal()).getEmail());
        }
	}

}
