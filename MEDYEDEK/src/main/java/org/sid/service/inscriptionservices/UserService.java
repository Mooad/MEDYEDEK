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
import org.sid.repositories.UtilisateurRepository;
import org.sid.utilities.RoleConsts;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService {

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private DomaineRepository domaineRepo;


    @Autowired
    private RegistrationConfirmation mailConfirmation;

    public UserService() {

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public void addUser(@RequestBody String utilisateurData) throws IOException, URISyntaxException {

        UtilisateurDto utilisateurDTO = new Gson().fromJson(utilisateurData, UtilisateurDto.class);

        AdresseMapper adresseMapper = new AdresseMapper();

        Adresse adresse = adresseMapper.mapAdresseFromUser(utilisateurDTO);
        List<Domaine> domaines = domaineRepo.findAll();
        Domaine chosen_domain = null;
        for (Domaine domaine : domaines) {
            if (domaine.getNomdomaine().equals(utilisateurDTO.getDomaine()))
                utilisateurDTO.setDomaine_id(domaine.getDomaine_id());
        }
        domaines.clear();
        //User Image
        String imageDataBytes = null;

        if (utilisateurDTO.getImage() != null) {
            imageDataBytes = utilisateurDTO.getImage().substring(utilisateurDTO.getImage().indexOf(",") + 1);

        }
        if (imageDataBytes != null) {
            utilisateurDTO.setImage(imageDataBytes);
            InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes()));
        }
        adresseRepository.save(adresse);

        //User Token
        StringBuilder token = new StringBuilder(RandomStringUtils.randomAlphanumeric(20));
        token.append(LocalDateTime.now().toString());
        Utilisateur registredUser = null;
        if (utilisateurDTO.getRole() == RoleConsts.DONNATEUR) {
            registredUser = utilisateurRepo.save(new Donnateur(utilisateurDTO.getFirstname(), utilisateurDTO.getLastname(),
                    utilisateurDTO.getEmail(), utilisateurDTO.getCin(), utilisateurDTO.getPhone_number(),
                    chosen_domain, adresse, utilisateurDTO.getConfirmationmdp(), utilisateurDTO.getImage(), token.toString()));
        }
        if (utilisateurDTO.getRole() == RoleConsts.BENEFICIAIRE) {
            registredUser = utilisateurRepo.save(new Beneficiaire(utilisateurDTO.getFirstname(), utilisateurDTO.getLastname(),
                    utilisateurDTO.getEmail(), utilisateurDTO.getCin(), utilisateurDTO.getPhone_number(),
                    chosen_domain, adresse, utilisateurDTO.getConfirmationmdp(), utilisateurDTO.getImage(), token.toString()));
        }

        URL url = null;
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("Confirmation-Template.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + "Confirmation-Template.txt");
        }

        String MailBody = Resources.toString(resource, StandardCharsets.UTF_8);

        mailConfirmation.sendMail("moad52@hotmail.fr", utilisateurDTO.getEmail(),
                MailBody, "Welcome to MEDYEDEK", utilisateurDTO.getFirstname(), registredUser.getUserToken());
    }

    @RequestMapping(value = "/user/token", method = RequestMethod.POST, headers = "Accept=application/json")
    public Optional<Utilisateur> getUserByToken(@RequestParam("token") String token) throws IOException {
        if (utilisateurRepo.getUserbyToken(token) != null && utilisateurRepo.getUserbyToken(token).size() > 0) {
            return utilisateurRepo.getUserbyToken(token).stream().findFirst();
        } else {
            throw new NotFoundException();
        }
    }

}

