package org.sid.services.nosql.document;

import java.util.List;
import java.util.Map;


import org.sid.services.dto.comment.CommentDto;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "CommentsGrappes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentsGrappes {
    @Id
    private String _id;
    private Integer user_id;
    private Integer post_id;
    private Map<String, List<CommentDto>> commentsTree;
}
