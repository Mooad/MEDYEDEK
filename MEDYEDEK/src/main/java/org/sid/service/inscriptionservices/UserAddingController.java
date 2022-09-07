package org.sid.service.inscriptionservices;

import com.sun.jersey.api.NotFoundException;
import org.sid.entities.User;
import org.sid.repositories.AdresseRepository;
import org.sid.repositories.UtilisateurRepository;
import org.sid.serviceproxy.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin
@RestController
@Service
public class UserAddingController {

    @Autowired
    private UtilisateurRepository utilisateurRepo;
    @Autowired
    private AdresseRepository adresseRepository;
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
    public Optional<User> getUserByToken(@RequestParam("token") String token) throws IOException {
        if (utilisateurRepo.getUserbyToken(token) != null && utilisateurRepo.getUserbyToken(token).size() > 0) {
            return utilisateurRepo.getUserbyToken(token).stream().findFirst();
        } else {
            throw new NotFoundException();
        }
    }


    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }
}

