package org.sid.services.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.sid.services.entities.User;
import org.sid.services.exception.exceptionBeans.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPasswordResetService {
    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    UserReactiveRepository userReactiveRepository;

    public UserPasswordResetService() {
    }

    public void updateUserPasswordAfterReset(String email, String temp_password) {

        List<User> utilisateur = new ArrayList<>();
        userReactiveRepository.findByEmail(email).subscribe(utilisateur::add);

        if (utilisateur.size() > 0) {
            userReactiveRepository.updateTemporaryPassword(email, temp_password).
                    subscribe(unused -> {
                        logger.info("Password has been Updated Succesfully");
                    });
        } else {
            throw new UserNotFoundException("User with email : " + email + " is not found");
        }
    }
}