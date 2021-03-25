package com.spiegelberger.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spiegelberger.springit.domain.Link;


@Repository
public interface LinkRepository extends JpaRepository<Link, Long>{

}
