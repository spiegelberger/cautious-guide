package com.spiegelberger.springit;

import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@SpringBootApplication
@EnableTransactionManagement
public class RedditCloneApplication {
	
	
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
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
