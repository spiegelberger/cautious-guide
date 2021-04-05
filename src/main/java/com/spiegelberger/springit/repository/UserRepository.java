package com.spiegelberger.springit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiegelberger.springit.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User>findByEmail(String email);
	
	Optional<User>findByEmailAndActivationCode(String email, String activationCode);
}
