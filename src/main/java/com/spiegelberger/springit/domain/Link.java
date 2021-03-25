package com.spiegelberger.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Link {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String url;
}
