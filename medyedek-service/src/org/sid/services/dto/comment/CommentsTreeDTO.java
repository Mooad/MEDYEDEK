package org.sid.services.dto.comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentsTreeDTO {
    private List<CommentDto> comments;

}


