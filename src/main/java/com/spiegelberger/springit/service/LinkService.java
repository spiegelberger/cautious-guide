package com.spiegelberger.springit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spiegelberger.springit.domain.Link;
import com.spiegelberger.springit.repository.LinkRepository;

@Service
public class LinkService {

	
	private LinkRepository linkRepository;

	@Autowired
	public LinkService(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}
	
	
	public List<Link>findAll(){
		return linkRepository.findAll();
	}
	
	public Optional<Link>findById(Long id){
		return linkRepository.findById(id);
	}
	
	public Link save(Link link) {
		return linkRepository.save(link);
	}
	
}
