package org.web.services.service.interaction;

import org.sid.services.dto.interaction.PostInteractionDto;

import org.sid.services.serviceproxy.PostInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@CrossOrigin
@RestController
@RequestMapping("/postInteraction")
public class PostInteractionController {

	@Autowired
	PostInteractionService postInteractionService;

	public PostInteractionController()
	{

	}

	@PostMapping("interact")
	public Mono<PostInteractionDto> interactWithPost(@RequestBody PostInteractionDto postInteractionDto)
	{
		return postInteractionService.interactWithPost(postInteractionDto);
	}

	@PostMapping("currentUserLikePost")
	public Mono<PostInteractionDto> postInteractionsState(@RequestBody PostInteractionDto postInteractionDto)
	{
		return postInteractionService.currentUserLikePost(postInteractionDto);
	}
	
}
