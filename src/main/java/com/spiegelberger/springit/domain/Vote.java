package com.spiegelberger.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Vote extends Auditable{

	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private short direction;
	
	@NonNull
	@ManyToOne
	private Link link;
}
