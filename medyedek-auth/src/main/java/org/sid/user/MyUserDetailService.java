package org.sid.user;

import org.sid.entities.User;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //searching valid users with the email provided
        List<User> users = utilisateurRepository.getUserLogin(email);
        Predicate<User> isNotValid = item -> item.isValid();

       // users.removeIf(isNotValid);

        MyUserDetails myUserDetails = null;
        if (!users.isEmpty() && users != null) {

            myUserDetails = new MyUserDetails(users.get(0));
        } else {
            throw new UsernameNotFoundException("user unknown");
        }

        return myUserDetails;
    }

    public Optional<User> usergetting()

    {
      return   utilisateurRepository.findById(1);

    }
}
