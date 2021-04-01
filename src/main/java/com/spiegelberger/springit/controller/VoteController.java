package com.spiegelberger.springit.controller;

import java.util.Optional;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spiegelberger.springit.domain.Link;
import com.spiegelberger.springit.domain.Vote;
import com.spiegelberger.springit.repository.LinkRepository;
import com.spiegelberger.springit.repository.VoteRepository;


@RestController
public class VoteController {
	
	private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }
    
    
    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, 
    				@PathVariable short direction, 
    				@PathVariable int voteCount) {
    	
    	Optional<Link>optionalLink = linkRepository.findById(linkID);
    	if(optionalLink.isPresent()) {
    		Link link = optionalLink.get();
    		Vote vote = new Vote(direction, link);
    		voteRepository.save(vote);
    		
    		int updatedVoteCount = voteCount + direction;
    		link.setVoteCount(updatedVoteCount);
    		linkRepository.save(link);
    		return updatedVoteCount;
    	}
    	return voteCount;
    }
}
