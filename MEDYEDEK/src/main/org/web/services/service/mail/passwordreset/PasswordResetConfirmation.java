package org.web.services.service.mail.passwordreset;

import com.sun.jersey.api.NotFoundException;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.sid.services.dto.user.SimpleUserDto;
import org.sid.services.handler.UserPasswordResetService;
import org.sid.services.mailconfirmation.ResetConfirmation;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;

@CrossOrigin("*")
@RestController
@Service
public class PasswordResetConfirmation {

    @Autowired
    UserReactiveRepository userReactiveRepository;
    @Autowired
    ResetConfirmation resetConfirmation;
    @Autowired
    UserPasswordResetService userPasswordResetService;

    @PostMapping("/change-pass")
    public SimpleUserDto sendResetPasswordEmailConfirmation(@RequestBody SimpleUserDto simpleUserDto) throws IOException {
        if(simpleUserDto.getUserEmail()!=null) {
            URL url = null;
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("Reset-Password-Confirmation.txt");
            if (resource == null) {
                throw new IllegalArgumentException("file not found! " + "Reset-Password-Confirmation.txt");
            }

            String temp_pass = generateRandomPassword(30);

            String MailBody = String.valueOf(resource);

            resetConfirmation.sendMail(simpleUserDto.getUserEmail(),
                    MailBody, "MEDYEDEK ACCOUNT PASSWORD RESET", temp_pass, "");


            userPasswordResetService.updateUserPasswordAfterReset(simpleUserDto.getUserEmail(), temp_pass);
        }
        else
        {
            throw  new  NotFoundException();
        }

        return simpleUserDto;
    }

    public static String generateRandomPassword(int len) {
        return RandomString.make(len);
    }

}

