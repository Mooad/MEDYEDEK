package org.sid.service.postservices;

import org.sid.dto.post.PostDto;
import org.sid.dto.user.LastPost;
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


	@PostMapping("all")
	public List<PostDto> allPosts(LastPost lastPost)
	{
		return postService.getAllPosts(lastPost);
	}

	@PostMapping("create")
	public List<PostDto> newPost()
	{
		return null;
	}




	
	
}
