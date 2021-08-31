package org.sid.service.mail.passwordreset;

import com.google.common.io.Resources;
import com.sun.jersey.api.NotFoundException;
import org.apache.commons.lang.RandomStringUtils;
import org.sid.dto.user.UserResetDto;
import org.sid.handler.UserPasswordResetService;
import org.sid.mailconfirmation.ResetConfirmation;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@CrossOrigin("*")
@RestController
@Service
public class PasswordResetConfirmation {

    @Autowired
    UtilisateurRepository userRepo;
    @Autowired
    ResetConfirmation resetConfirmation;
    @Autowired
    UserPasswordResetService userPasswordResetService;

    @PostMapping("/change-pass")
    public UserResetDto sendResetPasswordEmailConfirmation(@RequestBody UserResetDto userResetDto) throws IOException {
        if(userResetDto.getUserEmail()!=null) {
            URL url = null;
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("Reset-Password-Confirmation.txt");
            if (resource == null) {
                throw new IllegalArgumentException("file not found! " + "Reset-Password-Confirmation.txt");
            }
            String temp_pass = generateRandomPassword(30);

            String MailBody = Resources.toString(resource, StandardCharsets.UTF_8);

            resetConfirmation.sendMail(userResetDto.getUserEmail(),
                    MailBody, "MEDYEDEK ACCOUNT PASSWORD RESET", temp_pass, "");


            userPasswordResetService.updateUserPasswordAfterReset(userResetDto.getUserEmail(), temp_pass);
        }
        else
        {
            throw  new  NotFoundException();
        }

        return userResetDto;
    }

    public static String generateRandomPassword(int len) {
        return RandomStringUtils.randomAlphanumeric(len);
    }

}

