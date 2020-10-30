package org.sid.service.postservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sid.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repositories.postsRepository;


@CrossOrigin
@RestController
public class PostService {

	@Autowired postsRepository postsRepo;
	public PostService()
	{

	}


	@RequestMapping("/posts")
	public List<Post> AllProducts()
	{
		return postsRepo.findAll();
	}


	
	
}
