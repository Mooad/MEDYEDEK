package org.sid.services.serviceproxy.services;


import org.sid.services.dto.comment.CommentAttachementDto;

public interface PostCommentAttacher {

    void  attachCommentTreeIdentifierToPost(CommentAttachementDto commentAttachementDto);
}
