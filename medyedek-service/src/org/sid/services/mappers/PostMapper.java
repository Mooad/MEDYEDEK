package org.sid.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.sid.services.dto.post.PostDto;
import org.sid.services.entities.Post;
import org.sid.services.entities.User;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel="spring")
public interface PostMapper {

    @Mapping(target ="userImage" , source = "user.image")
    @Mapping(target ="user_id" , source = "user.user_id")
    PostDto posttoDto(Post post);

    Post dtoToPost(PostDto postDto);

    default Integer map(User user) {
        return user != null ?  user.getUser_id() : null;
    }

}
