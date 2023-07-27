package org.web.services.service.controller;

import org.sid.services.dto.post.ContentByPostDto;
import org.sid.services.entities.Content;
import org.sid.services.repositories.reactive.ContentReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@CrossOrigin
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentReactiveRepository contentReactiveRepository;

    public ContentController() {

    }
    @PostMapping("postContent")
    public Flux<Content> getContentOfPost(@RequestBody ContentByPostDto contentByPostDto) {
        return contentReactiveRepository.getContentsOfPost(Integer.parseInt(contentByPostDto.getPost_id()));
    }

}
