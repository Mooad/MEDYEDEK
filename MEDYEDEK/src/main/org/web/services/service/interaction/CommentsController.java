package org.web.services.service.interaction;


import org.sid.services.dto.comment.PostComments;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.nosql.document.Comment;
import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.serviceproxy.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/postComments")
public class CommentsController {


    @Autowired
    CommentServiceImpl commentService;
    @PostMapping("commnent")
    public Mono<Comment> commentPost(@RequestBody PostComments postComments)
    {
        return commentService.commentPost(postComments);
    }



    @PostMapping("search")
    public Mono<CommentsGrappes> searchPostComments(@RequestBody SearchCommentDto commentIdentifier)
    {
        return commentService.searchPostComments(commentIdentifier);
    }

}
