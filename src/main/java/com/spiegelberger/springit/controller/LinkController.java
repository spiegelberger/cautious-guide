package com.spiegelberger.springit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiegelberger.springit.domain.Link;
import com.spiegelberger.springit.repository.LinkRepository;


@RestController
@RequestMapping("/links")
public class LinkController {
	
	
	private LinkRepository linkRepository;
	
	@Autowired
	public LinkController(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	
	@GetMapping("/")
    public List<Link> list() {
       List<Link>links = linkRepository.findAll();
       return links;
    }

    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }
    
    @GetMapping("/{id}")
    public Optional<Link> read(@PathVariable Long id) {
        return linkRepository.findById(id);
    }
    
    @PutMapping("/{id}")
    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
        return linkRepository.save(link);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	linkRepository.deleteById(id);
    }
}
