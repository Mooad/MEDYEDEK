package org.sid.services.serviceproxy.services;

import org.sid.services.dto.comment.PostComments;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.nosql.document.Comment;
import org.sid.services.nosql.document.CommentsGrappes;
import reactor.core.publisher.Mono;



public interface CommentService {

     Mono<Comment>  commentPost(PostComments postComments);


     Mono<CommentsGrappes> searchPostComments(SearchCommentDto identifier);
}
