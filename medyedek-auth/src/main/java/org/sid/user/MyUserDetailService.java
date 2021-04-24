package org.sid.user;

import org.sid.entities.Utilisateur;
import org.sid.repositories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //searching valid users with the email provided
        List<Utilisateur> users = utilisateurRepository.getUserLogin(email);
        Predicate<Utilisateur> isNotValid = item -> item.isNotValid();

        users.removeIf(isNotValid);
        BCryptPasswordEncoder encoder = passwordEncoder();

        MyUserDetails myUserDetails = null;
        if (!users.isEmpty() && users != null) {

            myUserDetails = new MyUserDetails(users.get(0),encoder);
        } else {
            throw new UsernameNotFoundException("user unknown");
        }
        return myUserDetails;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
