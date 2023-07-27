package org.web.services.service.controller.comments;

import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.nosql.repositories.CommentsTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CommentsRestController {

    @Autowired
    private CommentsTreeRepository commentsTreeRepository;


    @GetMapping(value = "/all")
    public Mono<CommentsGrappes> findByID(){
        return commentsTreeRepository.findById("123456789");
    }


    @PostMapping("/commments")
    public Mono<CommentsGrappes> save(@RequestBody CommentsGrappes CommentTree){
        return commentsTreeRepository.save(CommentTree);
    }

}
