package com.spiegelberger.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment extends Auditable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private String body;
	
	@ManyToOne
	private Link link;

}
