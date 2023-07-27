package org.sid.services.dto.interaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.services.enumeration.InteractionType;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostInteractionDto {
    public Integer id;
    public Integer post_id;
    public Integer user_id;
    public InteractionType interactionType;
    public  String comment;
    public boolean NBlikesIncremented;
    public Integer likesNB;
}
