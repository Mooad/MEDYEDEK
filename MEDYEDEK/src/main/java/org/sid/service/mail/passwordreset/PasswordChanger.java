package org.sid.service.mail.passwordreset;


import org.sid.dto.password.PasswordDto;
import org.sid.entities.User;
import org.sid.handler.UserPasswordResetService;
import org.sid.mailconfirmation.ResetConfirmation;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;

@CrossOrigin("*")
@RestController
@Service
public class PasswordChanger {

    @Autowired
    UtilisateurRepository userRepo;
    @Autowired
    ResetConfirmation resetConfirmation;
    @Autowired
    UserPasswordResetService userPasswordResetService;

    /**
     * Changing the password after receiving a request to do so
     * @param passwordDto : the password object contains the new and temp password
     * @return :  the same object if all goes well
     * @throws IOException
     */
    @PostMapping("/change-pass-after-confirm")
    public PasswordDto passwordChangingAfterConfirmation(@RequestBody PasswordDto passwordDto) {
        if(passwordDto!=null) {
            List<User> users=  userRepo.getUserLogin(passwordDto.getEmail());
            if(users!= null && users.size()>0)
            {
               if(compareTempPassWithStoredOne.apply(users.get(0).getTemp_pass(),passwordDto.getTempPass()))
               {
                 users.get(0).setPassword(BCrypt.hashpw(passwordDto.newPass, BCrypt.gensalt(10)));
               }

            }
            userRepo.save(users.get(0));
        }
        else
        {
            throw  new IllegalArgumentException();
        }
        return passwordDto;
    }

        static BiFunction<String,String,Boolean> compareTempPassWithStoredOne =
                (existingPassword , receivedPassword) -> (existingPassword.equals(receivedPassword)) ? true:false;
}