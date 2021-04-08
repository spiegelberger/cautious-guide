package com.spiegelberger.springit.service;

import org.springframework.stereotype.Service;

import com.spiegelberger.springit.domain.Comment;
import com.spiegelberger.springit.repository.CommentRepository;

@Service
public class CommentService {

	
	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
}
