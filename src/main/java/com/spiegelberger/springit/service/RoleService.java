package com.spiegelberger.springit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiegelberger.springit.domain.Role;
import com.spiegelberger.springit.repository.RoleRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
		
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}


	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
