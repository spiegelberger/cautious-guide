package com.spiegelberger.springit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spiegelberger.springit.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User>findByEmail(String email);
	
	Optional<User>findByEmailAndActivationCode(String email, String activationCode);
}
