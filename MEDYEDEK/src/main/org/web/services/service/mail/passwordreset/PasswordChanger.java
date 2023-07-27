package org.web.services.service.mail.passwordreset;


import org.sid.services.dto.password.PasswordDto;
import org.sid.services.entities.User;
import org.sid.services.handler.UserPasswordResetService;
import org.sid.services.mailconfirmation.ResetConfirmation;
import org.sid.services.repositories.noreactive.IUserCrudReactiveRepository;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@CrossOrigin("*")
@RestController
@Service
public class PasswordChanger {

    @Autowired
    UserReactiveRepository userReactiveRepository;
    @Autowired
    IUserCrudReactiveRepository iUserCrudReactiveRepository;
    @Autowired
    ResetConfirmation resetConfirmation;
    @Autowired
    UserPasswordResetService userPasswordResetService;

    List<User> users = new ArrayList<>();

    /**
     * Changing the password after receiving a request to do so
     * @param passwordDto : the password object contains the new and temp password
     * @return :  the same object if all goes well
     * @throws IOException
     */
    @PostMapping("/change-pass-after-confirm")
    public PasswordDto passwordChangingAfterConfirmation(@RequestBody PasswordDto passwordDto) {

        users.clear();
        if(passwordDto!=null) {
            userReactiveRepository.findByEmail(passwordDto.getEmail()).subscribe(user -> users.add(user));
            if(users!= null && users.size()>0)
            {
               if(compareTempPassWithStoredOne.apply(users.get(0).getTemp_pass(),passwordDto.getTempPass()))
               {
                 users.get(0).setPassword(BCrypt.hashpw(passwordDto.newPass, BCrypt.gensalt(10)));
               }
                iUserCrudReactiveRepository.save(users.get(0)).subscribe();
            }
        }
        else
        {
            throw  new IllegalArgumentException();
        }
        return passwordDto;
    }

        static BiFunction<String,String,Boolean> compareTempPassWithStoredOne =
                String::equals;
}