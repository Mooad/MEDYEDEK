package org.sid.services.serviceproxy.Impl;

import org.sid.services.dto.comment.CommentDto;
import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.nosql.repositories.CommentsTreeRepository;
import org.sid.services.serviceproxy.services.CommentServiceLevelX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
@Component

public class CommentServiceLevelXImpl implements CommentServiceLevelX {


    @Autowired
    private CommentsTreeRepository commentsTreeRepository;


    @Override
    public Mono<CommentsGrappes> addReply(CommentDto commentDto) {
        return commentsTreeRepository.findById(commentDto.getParent())
                .flatMap(commentsGrappes -> {
                    if (commentsGrappes.getCommentsTree() == null) {
                        commentsGrappes.setCommentsTree(Map.of());
                    }

                    String parentKey = commentDto.getParent();
                    List<CommentDto> parentComments = commentsGrappes.getCommentsTree().getOrDefault(parentKey, List.of());
                    parentComments.add(commentDto);
                    commentsGrappes.getCommentsTree().put(parentKey, parentComments);

                    return commentsTreeRepository.save(commentsGrappes);
                });
    }
}