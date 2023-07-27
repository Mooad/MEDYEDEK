package org.web.services.service.controller.inscriptionservices;

import com.sun.jersey.api.NotFoundException;
import org.sid.services.entities.User;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.sid.services.serviceproxy.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@Service
public class UserAddingController {

    @Autowired
    UserReactiveRepository userRepository;
    @Autowired
    private RegistrationService registrationService;

    public UserAddingController() {

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public void addUser(@RequestBody String utilisateurData) throws IOException {
        registrationService.addAndValidateUser(utilisateurData);
    }

    @RequestMapping(value = "/user/token", method = RequestMethod.POST, headers = "Accept=application/json")
    public User getUserByToken(@RequestParam("token") String token) throws IOException {
        final User[] user = {null};
        userRepository.findByToken(token).subscribe(userFound -> user[0] = userFound);

        if (user[0] == null) {
            throw new NotFoundException();
        }
        return user[0];
    }


}

