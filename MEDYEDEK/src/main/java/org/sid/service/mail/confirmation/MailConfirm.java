package org.sid.service.mail.confirmation;

import org.sid.entities.Utilisateur;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@CrossOrigin
@Controller
@Service
public class MailConfirm {

    @Autowired
    UtilisateurRepository userRepo;



    @RequestMapping("/ValidateSignUp")
    public String validateSubscription(@RequestParam("token")String token) throws IOException {

        List<Utilisateur> users = userRepo.getUserbyToken(token);

        if (token.length() > 30 && users.size() > 0) {
            Utilisateur user = users.get(0);
            //calculating the difference bettween registration time and now
            LocalTime registerationTime = LocalTime.parse(token.substring(31));

            long minutes = registerationTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
            if (minutes <= 5L) {
                user.setIsValidUser("01");
                userRepo.save(user);

                return "WelcomeAfterSoubsc.html";
            } else {
                return "SoubscriptionNotCompleted.html";
            }
        }
        return null;
    }
    }

