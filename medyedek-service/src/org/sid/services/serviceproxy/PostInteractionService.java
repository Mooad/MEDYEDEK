package org.sid.services.serviceproxy;


import org.sid.services.dto.interaction.PostInteractionDto;
import org.sid.services.repositories.PostsRepository;
import org.sid.services.repositories.noreactive.IPostCrudInteractionRepository;
import org.sid.services.repositories.reactive.PostInteractionReactiveRepository;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class PostInteractionService {

    @Autowired
    PostInteractionReactiveRepository postInteractionReactiveRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostService postService;

    @Autowired
    UserReactiveRepository userRepository;

    @Autowired
    InteractionLikesHandler interactionLikesHandler;

    public Mono<PostInteractionDto> interactWithPost(PostInteractionDto postInteractionDto) {

        try {
            if (postInteractionDto.getPost_id() != null && postInteractionDto.getUser_id() != null) {

                return interactionLikesHandler.handleUserPostLikes(postInteractionDto);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public Mono<PostInteractionDto> currentUserLikePost(PostInteractionDto postInteractionDto) {

        try {
            if (postInteractionDto.getPost_id() != null && postInteractionDto.getUser_id() != null) {
                return postInteractionReactiveRepository.getLikePostInteractionWithUserId(postInteractionDto.post_id, postInteractionDto.user_id);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }




}