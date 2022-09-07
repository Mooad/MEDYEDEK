package org.sid.service.profile;

import org.sid.dto.profile.ProfileDto;
import org.sid.dto.user.SimpleUserDto;
import org.sid.serviceproxy.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    public ProfileController()
    { }

    @PostMapping("/profile")
    public ProfileDto userProfile(@RequestBody SimpleUserDto simpleUserDto)
    {
        //return the user Profile  by the email adress and null if no user found
        return simpleUserDto!=null ?  profileService.getUserProfile(simpleUserDto.userEmail) :  null;
    }
    @PostMapping("/synchProfile")
    public void syncProfile(@RequestBody ProfileDto profileDto)
    {
        //Synchronize the user informations into database
       profileService.syncUserInformations(profileDto);
     }
}
