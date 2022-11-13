package org.sid.serviceproxy;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.apache.commons.lang.RandomStringUtils;
import org.sid.dto.user.UtilisateurDto;
import org.sid.dto.mappers.AdresseMapper;
import org.sid.entities.*;
import org.sid.exception.exceptionBeans.AccountAlreadyExistsException;
import org.sid.mailconfirmation.RegistrationConfirmation;
import org.sid.repositories.AdresseRepository;
import org.sid.repositories.DomaineRepository;
import org.sid.repositories.RoleRepository;
import org.sid.repositories.UtilisateurRepository;
import org.sid.utilities.RoleConsts;
import org.sid.utilities.SomeUsefulMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RegistrationService {

    @Autowired
    private UtilisateurRepository utilisateurRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private DomaineRepository domaineRepo;
    @Autowired
    private RegistrationConfirmation mailConfirmation;

    public void addAndValidateUser(String utilisateurData) throws IOException {
        UtilisateurDto utilisateurDTO = new Gson().fromJson(utilisateurData, UtilisateurDto.class);

        AdresseMapper adresseMapper = new AdresseMapper();

        if (utilisateurDTO.getEmail() != null) {
            List<User> users = utilisateurRepo.getUserLogin(utilisateurDTO.getEmail());
            if (users.size() > 0) {
                throw new AccountAlreadyExistsException(utilisateurDTO.getEmail());
            }
        }
        //Getting ROLE + ADRESSE + DOMAINES of the user
        Address address = adresseMapper.mapAdresseFromUser(utilisateurDTO);
        adresseRepository.save(address);
        Role role = roleRepository.getOne(utilisateurDTO.getRole());

        Domaine chosen_domain = getUserDomaine(domaineRepo.findAll(), utilisateurDTO);

        //User Image
        if (utilisateurDTO.getImage() != null) {
            utilisateurDTO.setImage(utilisateurDTO.getImage().substring(utilisateurDTO.getImage().indexOf(",") + 1));
        }


        //User Token
        String token = generateConfirmationToken();
        User registredUser = null;
        utilisateurDTO.setConfirmationmdp(hash(utilisateurDTO.getConfirmationmdp()));
        if (utilisateurDTO.getRole() == RoleConsts.DONNATEUR) {
            registredUser = utilisateurRepo.save(new Donnateur(utilisateurDTO.getFirstname(), utilisateurDTO.getLastname(), registredUser.getPseudo(),
                    utilisateurDTO.getEmail(), utilisateurDTO.getCin(), utilisateurDTO.getPhone_number(),
                    chosen_domain, address, utilisateurDTO.getConfirmationmdp(), utilisateurDTO.getImage(), role, token));
        }
        if (utilisateurDTO.getRole() == RoleConsts.BENEFICIAIRE) {
            registredUser = utilisateurRepo.save(new Beneficiaire(utilisateurDTO.getFirstname(), utilisateurDTO.getLastname(), registredUser.getPseudo(),
                    utilisateurDTO.getEmail(), utilisateurDTO.getCin(), utilisateurDTO.getPhone_number(),
                    chosen_domain, address, utilisateurDTO.getConfirmationmdp(), utilisateurDTO.getImage(), role, token));
        }


        String MailBody = Resources.toString(SomeUsefulMethods.loadFromResources(getClass().getClassLoader()), StandardCharsets.UTF_8);

        mailConfirmation.sendMail(utilisateurDTO.getEmail(),
                MailBody, "Welcome to MEDYEDEK", utilisateurDTO.getFirstname(), registredUser.getUserToken());
    }


    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public String generateConfirmationToken() {
        StringBuilder token = new StringBuilder(RandomStringUtils.randomAlphanumeric(20));
        return token.append(LocalDateTime.now()).toString();
    }

    public Domaine getUserDomaine(List<Domaine> domaines, UtilisateurDto utilisateurDto) {

        for (Domaine domaine : domaines) {
            if (domaine.getNomdomaine().equals(utilisateurDto.getDomaine()))
                return domaine;
        }
        return null;
    }
}
