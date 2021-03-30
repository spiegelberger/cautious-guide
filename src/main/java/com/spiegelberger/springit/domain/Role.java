package com.spiegelberger.springit.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private Collection<User>users;
}
