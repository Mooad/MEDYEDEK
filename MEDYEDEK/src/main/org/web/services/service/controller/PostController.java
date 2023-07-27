package org.web.services.service.controller;

import org.sid.services.dto.post.PostDto;
import org.sid.services.dto.user.LastPost;
import org.sid.services.serviceproxy.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	PostService postService;

	public PostController()
	{

	}

	@PostMapping("all")
	public Flux<PostDto> allPosts(LastPost lastPost)
	{
		return postService.getAllPosts(lastPost);
	}

	@PostMapping("create")
	public List<PostDto> newPost()
	{
		return null;
	}

	
}
