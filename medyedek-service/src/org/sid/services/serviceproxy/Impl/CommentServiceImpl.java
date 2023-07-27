package org.sid.services.serviceproxy.Impl;

import org.sid.services.dto.comment.PostComments;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.nosql.document.Comment;
import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.nosql.repositories.CommentsTreeRepository;
import org.sid.services.serviceproxy.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsTreeRepository commentsTreeRepository;
    @Override
    public Mono<Comment> commentPost(PostComments postComments) {
        return null;
    }

    @Override
    public  Mono<CommentsGrappes> searchPostComments(SearchCommentDto identifier) {
        return commentsTreeRepository.findById(identifier.identifier);
    }
}
