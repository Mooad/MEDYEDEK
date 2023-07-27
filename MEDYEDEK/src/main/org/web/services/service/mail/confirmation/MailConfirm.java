package org.web.services.service.mail.confirmation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sid.services.entities.User;
import org.sid.services.repositories.noreactive.IUserCrudReactiveRepository;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@Service
public class MailConfirm {

    List<User> users = new ArrayList<>();

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    UserReactiveRepository userReactiveRepository;

    @Autowired
    IUserCrudReactiveRepository iUserCrudReactiveRepository;

    @RequestMapping("/ValidateSignUp")
    public String validateSubscription(@RequestParam("token")String token) throws IOException {
       users.clear();

       userReactiveRepository.findByToken(token).subscribe(logger::info);

        if (token.length() > 30 && users.size() > 0) {
            User user = users.get(0);
            //calculating the difference bettween registration time and now
            LocalTime registerationTime = LocalTime.parse(token.substring(31));

            long minutes = registerationTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
            if (minutes <= 5L) {
                user.setIsValidUser("01");
                iUserCrudReactiveRepository.save(user).subscribe();

                return "WelcomeAfterSoubsc.html";
            } else {
                return "SoubscriptionNotCompleted.html";
            }
        }
        return null;
    }
    }