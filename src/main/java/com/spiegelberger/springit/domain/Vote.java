package com.spiegelberger.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Vote {

	@Id
	@GeneratedValue
	private Long id;
	private int vote;
	
}
