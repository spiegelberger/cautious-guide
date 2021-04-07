package com.spiegelberger.springit.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spiegelberger.springit.config.AuditorAwareImpl;
import com.spiegelberger.springit.domain.Comment;
import com.spiegelberger.springit.domain.Link;
import com.spiegelberger.springit.domain.User;
import com.spiegelberger.springit.service.BeanUtil;
import com.spiegelberger.springit.service.CommentService;
import com.spiegelberger.springit.service.LinkService;
import com.spiegelberger.springit.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LinkController {

	private LinkService linkService;
	private CommentService commentService;
	private UserService userService;

	@Autowired
	public LinkController(LinkService linkService, CommentService commentService, UserService userService) {
		this.linkService = linkService;
		this.commentService = commentService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkService.findAll());
		return "link/list";
	}

	@GetMapping("/link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> optionalLink = linkService.findById(id);
		if (optionalLink.isPresent()) {
			Link currentLink = optionalLink.get();

			Comment comment = new Comment();
			comment.setLink(currentLink);

			model.addAttribute("comment", comment);
			model.addAttribute("link", currentLink);
			model.addAttribute("success", model.containsAttribute("success"));
			return "link/view";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/link/submit")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		return "link/submit";
	}

	/*
	 * This is my solution which tries to solve the reported problem of Dan Vega's
	 * original code. Now it is possible for registered users to add links.
	 */
	@PostMapping("/link/submit")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		User user = null;
		Optional<String> optionalEmail = BeanUtil.getBean(AuditorAwareImpl.class).getCurrentAuditor();
			if (optionalEmail.isPresent()) {
				Optional<User> optionalUser = userService.findByEmail(optionalEmail.get());
				user = (optionalUser.isPresent()) ? optionalUser.get() : null;
				}
			
		if (bindingResult.hasErrors()) {
			log.info("Validation Errors were found while submiting a new link");
			link.setUser(user);
			model.addAttribute("link", link);
			return "link/submit";
		} else {
			link.setUser(user);
			linkService.save(link);
			log.info("New Link Saved Successfully");
			redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success", true);
			return "redirect:/link/{id}";
		}

	}

	@Secured({ "ROLE_USER" })
	@PostMapping("link/comments")
	public String addComment(@Valid Comment comment, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			log.info("Something went wrong.");
		} else {
			log.info("New Comment was saved successfully!");
			commentService.save(comment);
		}
		return "redirect:/link/" + comment.getLink().getId();
	}

}
