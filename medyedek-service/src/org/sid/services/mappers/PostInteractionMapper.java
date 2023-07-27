package org.sid.services.mappers;


import org.sid.services.dto.interaction.PostInteractionDto;
import org.sid.services.entities.Postinteraction;
import org.springframework.stereotype.Component;

@Component
public class PostInteractionMapper {

public  static Postinteraction dtoToEntity(PostInteractionDto dto)
{
    Postinteraction entity = new Postinteraction();
    entity.setId(dto.id);
    entity.setInteraction_type(dto.getInteractionType());
    entity.setComment(dto.comment);
    entity.setPost_id(dto.getPost_id());
    entity.setUser_id(dto.getUser_id());

    return entity;

}

    public  static PostInteractionDto entityToDto(Postinteraction entity)
    {
        PostInteractionDto dto = new PostInteractionDto();
        dto.setId(entity.id);
        dto.setComment(entity.getComment());
        dto.setComment(entity.comment);
        dto.setInteractionType(entity.interaction_type);
        dto.setPost_id(entity.getPost_id());
        dto.setUser_id(entity.getUser_id());

        return dto;

    }




}
