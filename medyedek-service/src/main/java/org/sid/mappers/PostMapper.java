package org.sid.mappers;

import org.sid.dto.post.PostDto;
import org.sid.entities.Post;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface PostMapper {

    PostDto PosttoDto(Post post);

    Post dtoToPost(PostDto postDto);
}
