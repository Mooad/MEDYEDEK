package org.sid.service.postservices;

import org.sid.dto.post.PostDto;
import org.sid.serviceproxy.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


	@GetMapping("all")
	public List<PostDto> allPosts()
	{
		return postService.getAllPosts();
	}

	@PostMapping("create")
	public List<PostDto> newPost()
	{
		return postService.getAllPosts();
	}




	
	
}
