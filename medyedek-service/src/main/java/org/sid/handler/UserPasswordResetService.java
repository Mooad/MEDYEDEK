package org.sid.handler;

import com.sun.jersey.api.NotFoundException;
import org.sid.entities.User;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserPasswordResetService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public UserPasswordResetService() {
    }

    ;
    @Transactional
    public void updateUserPasswordAfterReset(String email, String temp_password) {
        boolean password_updated = false;
        
        List<User> utilisateur = utilisateurRepository.getUserLogin(email);

        if (utilisateur.size() > 0) {
            utilisateurRepository.updateTemporaryPassword(email, temp_password);
        } else {
            throw new NotFoundException();
        }
    }
}