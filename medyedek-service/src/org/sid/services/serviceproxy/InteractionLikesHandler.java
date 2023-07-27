package org.sid.services.serviceproxy;



import org.sid.services.dto.interaction.PostInteractionDto;
import org.sid.services.enumeration.InteractionType;
import org.sid.services.mappers.PostInteractionMapper;
import org.sid.services.repositories.reactive.PostInteractionReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class InteractionLikesHandler {

    @Autowired
    PostInteractionReactiveRepository postInteractionReactiveRepository;

    @Autowired
    PostService postService;

    //1

    // utilisateur fait un like alors qu'il n'a jamais fait de like avant

    //2

    // utilisateur fait un like alors qu'il a déja liker

    //3

    //

    public Mono<PostInteractionDto> handleUserPostLikes(PostInteractionDto postInteractionDto) {

        Mono<PostInteractionDto> postInteractionResult = postInteractionReactiveRepository.getLikePostInteractionWithUserId(postInteractionDto.post_id, postInteractionDto.user_id);

        postInteractionResult.hasElement().subscribe(aBoolean -> {
            if (!aBoolean) {
                postInteractionReactiveRepository.savePostInteraction(PostInteractionMapper.dtoToEntity(postInteractionDto));
            }
            if (postInteractionDto.getInteractionType().equals(InteractionType.DISLIKE)) {
                postInteractionReactiveRepository.deletePostInteraction(postInteractionDto.post_id, postInteractionDto.user_id).subscribe();
            }
        });

        return postInteractionReactiveRepository.getLikePostInteractionWithUserId(postInteractionDto.post_id, postInteractionDto.user_id);


    }

}
