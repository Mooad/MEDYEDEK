package org.web.services.service.interaction;


import org.apache.commons.lang.StringUtils;
import org.sid.services.dto.comment.CommentAttachementDto;
import org.sid.services.dto.comment.CommentDto;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.serviceproxy.Impl.CommentServiceLevel0Impl;
import org.sid.services.serviceproxy.Impl.PostCommentAttacherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/postComments")
public class CommentsController {


    @Autowired
    CommentServiceLevel0Impl commentService;

    @Autowired
    PostCommentAttacherImpl postCommentAttacher;

    @PostMapping("comment")
    public Mono<CommentsGrappes> commentPost(@RequestBody CommentDto commentDto)
    {
        System.out.println("Adding Comment to Post "+ commentDto.getPost_id());
        return commentService.commentPost(commentDto);
    }
    @PostMapping("attachComment")
    public void attachCommentGrappeToPost(@RequestBody CommentAttachementDto commentAttachementDto)
    {
        System.out.println("This is a comment attched");
         postCommentAttacher.attachCommentTreeIdentifierToPost(commentAttachementDto);
    }



    @PostMapping("search")
    public Mono<CommentsGrappes> searchPostComments(@RequestBody SearchCommentDto commentIdentifier)
    {
        return (StringUtils.isNotBlank(commentIdentifier.identifier) ? commentService.searchPostComments(commentIdentifier) : null);
    }

    @PostMapping("updateTree")
    public Mono<CommentsGrappes> updatePostComment(@RequestBody CommentsGrappes commentsGrappes)
    {
        return commentService.updateCommentTree(commentsGrappes);    }

}
