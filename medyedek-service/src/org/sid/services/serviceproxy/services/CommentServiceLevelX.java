package org.sid.services.serviceproxy.services;

import org.sid.services.dto.comment.CommentDto;


public interface CommentServiceLevelX {

    void addReply(CommentDto commentDto, String identifier);

}
