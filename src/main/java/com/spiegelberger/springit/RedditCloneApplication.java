package com.spiegelberger.springit;

import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@SpringBootApplication
public class RedditCloneApplication {

	private static final Logger logger = LoggerFactory.getLogger(RedditCloneApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}
	
	@Bean
	PrettyTime getPrettyTime() {
		return new PrettyTime();
	}
	
	/*
	 * Spring Security Thymeleaf extras
	 */
	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
	


}
