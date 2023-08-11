package org.sid.services.serviceproxy.Impl;

import org.apache.commons.lang.StringUtils;
import org.sid.services.dto.comment.CommentDto;
import org.sid.services.dto.comment.CommentsTreeDTO;
import org.sid.services.nosql.repositories.CommentsTreeRepository;
import org.sid.services.serviceproxy.services.CommentServiceLevelX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component

public class CommentServiceLevelXImpl implements CommentServiceLevelX {


    @Autowired
    private CommentsTreeRepository commentsTreeRepository;


    @Override
    public void addReply(CommentDto commentDto , String identifier) {
        if(StringUtils.isNotBlank(identifier))
        {
            commentsTreeRepository.findById(identifier.trim()).subscribe(commentsGrappes ->
            {
                addSubCommentToParent(commentsGrappes.getCommentsTree().get("comments"),commentDto);
                commentsTreeRepository.save(commentsGrappes).subscribe();
            });
        }
    }




    public void addSubCommentToParent(List<CommentDto>  commentsTree, CommentDto reply) {
        for (CommentDto comment : commentsTree) {
            if (Objects.equals(comment.get_id(), reply.getParent())) {
                if ((comment.getCommentsTree() ==null) || comment.getCommentsTree().getComments() == null) {
                    comment.setCommentsTree(new CommentsTreeDTO());
                    comment.getCommentsTree().setComments(new ArrayList<>());
                }
               comment.getCommentsTree().getComments().add(reply);
            } else if (comment.getCommentsTree().getComments() != null) {
                this.addSubCommentToParent(comment.getCommentsTree().getComments(), reply);
            }
        }

    }

}