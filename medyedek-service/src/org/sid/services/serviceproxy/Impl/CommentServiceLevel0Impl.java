package org.sid.services.serviceproxy.Impl;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.sid.services.dto.comment.CommentDto;
import org.sid.services.dto.comment.SearchCommentDto;
import org.sid.services.exception.exceptionBeans.DataSavingException;
import org.sid.services.nosql.document.CommentsGrappes;
import org.sid.services.nosql.repositories.CommentsTreeRepository;
import org.sid.services.repositories.reactive.PostReactiveRepository;
import org.sid.services.serviceproxy.services.CommentServiceLevel0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class CommentServiceLevel0Impl implements CommentServiceLevel0 {

    @Autowired
    private CommentsTreeRepository commentsTreeRepository;
    @Autowired
    PostReactiveRepository postReactiveRepository;


    @Override
    public Mono<CommentsGrappes> commentPost(CommentDto commentDto) {

        Mono<CommentsGrappes> commentsGrappesMono = null;
        // Setting the Id
        commentDto.set_id(new ObjectId().toString());

        // Fist Comment that intialises the tree
        if (commentDto.getParent() == null) {
            CommentsGrappes commentsGrappes = new CommentsGrappes();
            commentsGrappes.setPost_id(commentDto.getPost_id());
            commentsGrappes.setUser_id(commentDto.getUser_id());
            commentsGrappes.setCommentsTree(Collections.singletonMap("comments", Collections.singletonList(commentDto)));
            commentsGrappesMono = commentsTreeRepository.save(commentsGrappes);

        } else {
            // Fetch the existing CommentsGrappes object using the parent comment ID
            return commentsTreeRepository.findById(commentDto.getParent().trim())
                    .flatMap(existingCommentsGrappes -> {
                        // Extract the existing comments tree from the fetched object
                        Map<String, List<CommentDto>> commentsTree = existingCommentsGrappes.getCommentsTree();
                        // Get the "comments" list from the comments tree
                        List<CommentDto> comments = commentsTree.get("comments");
                        // Add the new comment to the "comments" list
                        comments.add(commentDto);
                        // Save the updated CommentsGrappes object
                        return commentsTreeRepository.save(existingCommentsGrappes);
                    });
        }
        return commentsGrappesMono;

    }

    @Override
    public  Mono<CommentsGrappes> searchPostComments(SearchCommentDto identifier) {
        return commentsTreeRepository.findById(StringUtils.trim(identifier.identifier));
    }


    @Override
    public Mono<CommentsGrappes> updateCommentTree(CommentsGrappes commentsGrappes) {

        postReactiveRepository.deleteCommentGrappefromComment(commentsGrappes.getPost_id());


        if (commentsGrappes.getCommentsTree().get("comments").size() == 1) {
            commentsTreeRepository.delete(commentsGrappes);
        } else {
            return commentsTreeRepository.save(commentsGrappes);
        }

        throw new DataSavingException("");
    }
}
