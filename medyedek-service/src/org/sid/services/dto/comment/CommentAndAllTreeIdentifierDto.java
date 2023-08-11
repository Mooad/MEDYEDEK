package org.sid.services.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentAndAllTreeIdentifierDto {
    private CommentDto commentDto;
    private String identifier;
}
