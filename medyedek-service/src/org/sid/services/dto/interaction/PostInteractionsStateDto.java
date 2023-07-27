package org.sid.services.dto.interaction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostInteractionsStateDto {

    public Integer numberOfLikes;
    public boolean currentUserLike;
    public Integer user_id;
    public Integer commentCount;
    public Integer ShareCount;


}
