package org.sid.services.serviceproxy;

import com.google.gson.Gson;
import org.apache.commons.lang.RandomStringUtils;

import org.sid.services.dto.user.UserDto;
import org.sid.services.dto.mappers.AdresseMapper;
import org.sid.services.entities.*;
import org.sid.services.exception.exceptionBeans.AccountAlreadyExistsException;
import org.sid.services.mailconfirmation.RegistrationConfirmation;

import org.sid.services.repositories.AdresseRepository;
import org.sid.services.repositories.DomaineRepository;
import org.sid.services.repositories.RoleRepository;
import org.sid.services.repositories.noreactive.IUserCrudReactiveRepository;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.sid.services.utilities.RoleConsts;
import org.sid.services.utilities.SomeUsefulMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationService {

    @Autowired
    private UserReactiveRepository userReactiveRepository;
    @Autowired
    private IUserCrudReactiveRepository userCrudReactiveRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdresseRepository adresseRepository;
    @Autowired
    private DomaineRepository domaineRepo;
    @Autowired
    private RegistrationConfirmation mailConfirmation;


    public void addAndValidateUser(String utilisateurData) throws IOException {
        UserDto userDto = new Gson().fromJson(utilisateurData, UserDto.class);
        List<Domaine> domaineList = new ArrayList<>();
        AdresseMapper adresseMapper = new AdresseMapper();

        if (userDto.getEmail() != null) {
            List<User> users = new ArrayList<>();
           userReactiveRepository.findByEmail(userDto.getEmail()).subscribe(users::add);

            if (users.size() > 0) {
                throw new AccountAlreadyExistsException(userDto.getEmail());
            }
        }
        //Getting ROLE + ADRESSE + DOMAINES of the user
        Adress address = adresseMapper.mapAdresseFromUser(userDto);
        adresseRepository.save(address).subscribe();
        Role role = roleRepository.findById(userDto.getRole()).block();
        domaineRepo.findAll().subscribe(domaineList::add);
        Domaine chosen_domain = getUserDomaine(domaineList, userDto);

        //User Image
        if (userDto.getImage() != null) {
            userDto.setImage(userDto.getImage().substring(userDto.getImage().indexOf(",") + 1));
        }


        //User Token
        String token = generateConfirmationToken();
        final User[] registredUser = {new User()};

        userDto.setConfirmationmdp(hash(userDto.getConfirmationmdp()));
        if (userDto.getRole() == RoleConsts.DONNATEUR) {
          userCrudReactiveRepository.save(new Donnateur(userDto.getFirstname(), userDto.getLastname(), userDto.getPseudo(),
                    userDto.getEmail(), userDto.getCin(), userDto.getPhone_number(),
                    chosen_domain, address, userDto.getConfirmationmdp(), userDto.getImage(), role, token)).subscribe(donnateur -> registredUser[0] = donnateur);
        }
        if (userDto.getRole() == RoleConsts.BENEFICIAIRE) {
          userCrudReactiveRepository.save(new Beneficiaire(userDto.getFirstname(), userDto.getLastname(), userDto.getPseudo(),
                    userDto.getEmail(), userDto.getCin(), userDto.getPhone_number(),
                    chosen_domain, address, userDto.getConfirmationmdp(), userDto.getImage(), role, token)).subscribe(donnateur -> registredUser[0] = donnateur);
        }

        String MailBody = String.valueOf(SomeUsefulMethods.loadFromResources(getClass().getClassLoader()));

        assert registredUser[0] != null;
        mailConfirmation.sendMail(userDto.getEmail(),
                MailBody, "Welcome to MEDYEDEK", userDto.getFirstname(), registredUser[0].getUserToken());
    }


    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public String generateConfirmationToken() {
        StringBuilder token = new StringBuilder(RandomStringUtils.randomAlphanumeric(20));
        return token.append(LocalDateTime.now()).toString();
    }

    public Domaine getUserDomaine(List<Domaine> domaines, UserDto userDto) {

        for (Domaine domaine : domaines) {
            if (domaine.getNomdomaine().equals(userDto.getDomaine()))
                return domaine;
        }
        return null;
    }
}
