package org.web.services.service.interaction;


import org.sid.services.dto.comment.CommentDto;
import org.sid.services.serviceproxy.Impl.CommentServiceLevelXImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/postReply")
public class CommentReplyController {


    @Autowired
    CommentServiceLevelXImpl commentService;


    @PostMapping("reply")
    public void replyInPost(@RequestBody CommentDto commentDto)
    {
        System.out.println("Adding Comment to Post "+ commentDto.getPost_id());
         commentService.addReply(commentDto).subscribe(commentsGrappes -> System.out.println(commentsGrappes.get_id()));
    }

}
