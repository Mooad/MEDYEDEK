package org.sid.service.inscriptionservices;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.core.util.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.sid.dto.UtilisateurDto;
import org.sid.dto.mappers.AdresseMapper;
import org.sid.entities.*;
import org.sid.mailconfirmation.RegistrationConfirmation;
import org.sid.repositories.AdresseRepository;
import org.sid.repositories.DomaineRepository;
import org.sid.repositories.RoleRepository;
import org.sid.repositories.UtilisateurRepository;
import org.sid.serviceproxy.InscriptionService;
import org.sid.utilities.RoleConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
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
    private InscriptionService inscriptionService;

    public UserAddingController() {

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public void addUser(@RequestBody String utilisateurData) throws IOException {
        inscriptionService.addAndValidateUser(utilisateurData);
    }

    @RequestMapping(value = "/user/token", method = RequestMethod.POST, headers = "Accept=application/json")
    public Optional<Utilisateur> getUserByToken(@RequestParam("token") String token) throws IOException {
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

