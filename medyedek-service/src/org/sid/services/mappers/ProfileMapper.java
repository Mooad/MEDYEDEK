package org.sid.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.sid.services.dto.profile.ProfileDto;
import org.sid.services.entities.User;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface ProfileMapper {


    @Mapping(target ="role.rolename" , source = "user.role.rolename")
    @Mapping(target ="role.role_id" , source = "user.role.role_id")
    ProfileDto UtilisateurToProfileDto(User user);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }
}