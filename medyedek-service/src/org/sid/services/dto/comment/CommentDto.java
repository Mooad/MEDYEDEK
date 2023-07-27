package org.sid.services.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    private String id;
    private String text;
    private String content;
    private String post_id;
    private String user_id;
    private Integer level;
    private String parent;
    private CommentsTreeDTO commentsTree;

}
