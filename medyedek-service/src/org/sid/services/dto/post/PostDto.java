package org.sid.services.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.services.entities.Content;
import org.sid.services.entities.Typepost;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    public int post_id;
    public int user_id;
    public Typepost typepost;
    public String textContent;
    public String userImage;
    public List<Content> postContent;
    public Integer likesNB;
    public Integer commentsNB;
    public Integer sharesNB;
    public String  commentContent;
}