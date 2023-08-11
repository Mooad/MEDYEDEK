package org.sid.services.serviceproxy.Impl;

import org.sid.services.dto.comment.CommentAttachementDto;
import org.sid.services.repositories.reactive.PostReactiveRepository;
import org.sid.services.serviceproxy.services.PostCommentAttacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostCommentAttacherImpl implements PostCommentAttacher {

    @Autowired
    PostReactiveRepository postReactiveRepository;

    @Override
    public void attachCommentTreeIdentifierToPost(CommentAttachementDto commentAttachementDto) {
        postReactiveRepository.saveCommentGrappeToComment(commentAttachementDto.getPost_id(),commentAttachementDto.getComment_id());
    }



}
