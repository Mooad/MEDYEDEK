package org.web.services.service.interaction;


import org.sid.services.dto.comment.CommentAndAllTreeIdentifierDto;
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
    public void replyInPost(@RequestBody CommentAndAllTreeIdentifierDto commentAndAllTreeIdentifierDto) {

         commentService.addReply(commentAndAllTreeIdentifierDto.getCommentDto(),commentAndAllTreeIdentifierDto.getIdentifier());
    }

}
