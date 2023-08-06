package org.sid.services.dto.comment;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    @Id
    private String _id;
    private String text;
    private String content;
    private Integer post_id;
    private Integer user_id;
    private Integer level;
    private String parent;
    private CommentsTreeDTO commentsTree;



}
