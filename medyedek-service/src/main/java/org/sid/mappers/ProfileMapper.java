package org.sid.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.sid.dto.profile.ProfileDto;
import org.sid.entities.User;

 @Mapper(componentModel="spring")
public interface ProfileMapper {
    @Mappings({
            @Mapping(target = "image", source = "image"),
            @Mapping(target = "address", source = "address")

    })
    ProfileDto UtilisateurToProfileDto(User user);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }
    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}