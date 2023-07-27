package org.sid.services.serviceproxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sid.services.mappers.ProfileMapper;
import org.sid.services.dto.mappers.AdresseMapper;
import org.sid.services.dto.profile.ProfileDto;
import org.sid.services.entities.Beneficiaire;
import org.sid.services.entities.Donnateur;
import org.sid.services.entities.User;

import org.sid.services.repositories.noreactive.IUserCrudReactiveRepository;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    UserReactiveRepository userReactiveRepository;

    @Autowired
    IUserCrudReactiveRepository iUserCrudReactiveRepository;
    @Autowired
    ProfileMapper profileMapper;

    @Autowired
    AdresseMapper adresseMapper;

    List<User> users = new ArrayList<>();

    /**
     * Class used to get all the postDto and return them to front End App
     *
     * @return
     */
    public Mono<ProfileDto> getUserProfile(String email) {

        return userReactiveRepository.findProfileByEmail(email);

    }

    /**
     * synchronise the user wih the received infromations modified by the user
     * @param profileDto
     */
    public void syncUserInformations(ProfileDto profileDto) {
        userReactiveRepository.findByEmail(profileDto.email).subscribe(user -> {
            iUserCrudReactiveRepository.save(applyProfileModifications(profileDto, user));
        });
    }

    /**
     * just a Mapper that merges modification and returns the user modified
     * @param profileDto
     * @param user
     * @retruns user
     */
    private User applyProfileModifications(ProfileDto profileDto,User user)
    {
        user.setLastname(profileDto.lastname);
        user.setFirstname(profileDto.firstname);
        user.setPhone_number(profileDto.phone_number);
        user.setEmail(profileDto.email);
        user.setPseudo(profileDto.pseudo);
        user.setImage(profileDto.image);
       // adresseMapper.mapAdresseFromProfileDto(profileDto,user);
        return user;
    }


}