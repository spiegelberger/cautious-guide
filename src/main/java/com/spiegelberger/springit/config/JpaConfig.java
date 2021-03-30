package com.spiegelberger.springit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/*
 * The auditorAwareRef configures the AuditorAware bean to be used 
 * to lookup the current principal.
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

	@Bean
	public AuditorAware<String>auditorAware(){
		return new AuditorAwareImpl();
	}
}
