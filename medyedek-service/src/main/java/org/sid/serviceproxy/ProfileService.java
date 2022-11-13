package org.sid.serviceproxy;

import org.sid.dto.mappers.AdresseMapper;
import org.sid.dto.profile.ProfileDto;
import org.sid.entities.Beneficiaire;
import org.sid.entities.Donnateur;
import org.sid.entities.User;
import org.sid.mappers.ProfileMapper;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ProfileMapper profileMapper;

    @Autowired
    AdresseMapper adresseMapper;

    /**
     * Class used to get all the postDto and return them to front End App
     *
     * @return
     */
    public ProfileDto getUserProfile(String email) {

        List<User> users = utilisateurRepository.getUserLogin(email);
        User user = null;
        if (!users.isEmpty()) {
            user = users.get(0);
        }
        return profileMapper.UtilisateurToProfileDto(user);

    }

    /**
     * synchronise the user wih the received infromations modified by the user
     * @param profileDto
     */
    public void syncUserInformations(ProfileDto profileDto) {
        List<User> users = utilisateurRepository.getUserLogin(profileDto.email);
        User user=null;
        if(profileDto.role.getRolename().equals("DONN")) {
             user = new Donnateur();
        }
        else
        {
            user = new Beneficiaire();
        }
        if (!users.isEmpty()) {
            user = users.get(0);
        }
        utilisateurRepository.save(applyProfileModifications(profileDto,user));
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
        user.setImage(profileDto.image.getBytes());
        adresseMapper.mapAdresseFromProfileDto(profileDto,user);
        return user;
    }


}