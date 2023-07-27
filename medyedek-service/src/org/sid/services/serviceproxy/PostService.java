package org.sid.services.serviceproxy;

import org.apache.commons.lang.StringUtils;
import org.sid.services.dto.user.LastPost;
import org.sid.services.mappers.PostInteractionMapper;
import org.sid.services.mappers.PostMapper;
import org.sid.services.dto.interaction.PostInteractionDto;
import org.sid.services.entities.Post;

import org.sid.services.dto.post.PostDto;
import org.sid.services.enumeration.InteractionType;
import org.sid.services.repositories.noreactive.IPostCrudInteractionRepository;
import org.sid.services.repositories.noreactive.IPostCrudRepository;
import org.sid.services.repositories.reactive.ContentReactiveRepository;
import org.sid.services.repositories.reactive.PostInteractionReactiveRepository;
import org.sid.services.repositories.reactive.PostReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class PostService {

    @Autowired
    IPostCrudInteractionRepository postInteractionReposRepo;
    @Autowired
    PostReactiveRepository postsRepo;
    @Autowired
    PostReactiveRepository postReactiveRepository;
    @Autowired
    ContentReactiveRepository contentReactiveRepository;
    @Autowired
    PostInteractionReactiveRepository postInteractionReactiveRepository;
    @Autowired
    PostMapper postMapper;

    /**
     * Class used to get all the postDto and return them to front End App
     *
     * @return
     */
    public Flux<PostDto> getAllPosts(LastPost lastPost) {

        return postsRepo.getAllPosts();
    }


}