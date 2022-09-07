package org.sid.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.entities.Content;
import org.sid.entities.Typepost;
import org.sid.entities.User;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {


    public int id_post;
    public Typepost typepost;
    public String textContent;
    public User user;
    public List<Content> postContent;
    
}
