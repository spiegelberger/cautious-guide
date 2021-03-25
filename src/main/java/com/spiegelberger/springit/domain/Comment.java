package com.spiegelberger.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Comment extends Auditable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String body;
	
	@ManyToOne
	private Link link;

}
