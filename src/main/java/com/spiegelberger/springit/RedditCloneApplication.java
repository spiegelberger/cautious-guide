package com.spiegelberger.springit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.spiegelberger.springit.domain.Comment;
import com.spiegelberger.springit.domain.Link;
import com.spiegelberger.springit.repository.CommentRepository;
import com.spiegelberger.springit.repository.LinkRepository;

@SpringBootApplication
@EnableJpaAuditing
public class RedditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditCloneApplication.class, args);
	}
	
	
	/*
	 * This is also a possibility to initialize database:
	 */
	
//	@Bean
//	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
//		return args -> {
//			Link link =new Link("Getting started with Spring Boot2", "https://therealdanvega.com/spring-boot-2");			      
//			      Comment comment = new Comment("I like that!!!");
//			      linkRepository.save(link);
//			      commentRepository.save(comment);
//			      link.addComment(comment);
//		};
//	}

}
