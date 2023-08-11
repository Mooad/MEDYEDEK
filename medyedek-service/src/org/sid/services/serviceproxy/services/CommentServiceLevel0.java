package org.sid.services.serviceproxy.services;

import org.sid.services.dto.comment.CommentDto;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.nosql.document.CommentsGrappes;
import reactor.core.publisher.Mono;



public interface CommentServiceLevel0 {

     Mono<CommentsGrappes> commentPost(CommentDto commentDto);


     Mono<CommentsGrappes> searchPostComments(SearchCommentDto identifier);


     Mono<CommentsGrappes> updateCommentTree(CommentsGrappes commentsGrappes);
}
