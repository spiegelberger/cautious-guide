package com.spiegelberger.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiegelberger.springit.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
