package org.sid.services.serviceproxy.services;

import org.sid.services.dto.comment.CommentDto;
import org.sid.services.nosql.document.CommentsGrappes;
import reactor.core.publisher.Mono;

public interface CommentServiceLevelX {

    Mono<CommentsGrappes> addReply(CommentDto commentDto);

}
