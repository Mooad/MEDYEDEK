package org.web.services.service.controller.profile;

import org.sid.services.dto.profile.ProfileDto;
import org.sid.services.dto.user.SimpleUserDto;
import org.sid.services.serviceproxy.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    public ProfileController()
    { }

    @PostMapping("/profile")
    public Mono<ProfileDto> userProfile(@RequestBody SimpleUserDto simpleUserDto)
    {
        //return the user Profile  by the email adress and null if no user found
        return  profileService.getUserProfile(simpleUserDto.userEmail);
    }
    @PostMapping("/synchProfile")
    public void syncProfile(@RequestBody ProfileDto profileDto)
    {
        //Synchronize the user informations into database
       profileService.syncUserInformations(profileDto);
     }
}
