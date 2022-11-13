package org.sid.mappers;

import org.sid.dto.post.PostDto;
import org.sid.entities.Post;
import org.mapstruct.Mapper;


 @Mapper(componentModel="spring")
public interface PostMapper {

    PostDto posttoDto(Post post);

    Post dtoToPost(PostDto postDto);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }
    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }

}
