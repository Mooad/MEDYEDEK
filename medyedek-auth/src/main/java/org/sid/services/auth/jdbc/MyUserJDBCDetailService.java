package org.sid.services.auth.jdbc;

import org.sid.services.entities.User;
import org.sid.services.repositories.noreactive.IUserCrudReactiveRepository;
import org.sid.services.repositories.reactive.UserReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Component
public class MyUserJDBCDetailService implements UserDetailsService {

    @Autowired
    UserReactiveRepository userReactiveRepository;
    @Autowired

    IUserCrudReactiveRepository userCrudReactiveRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //searching valid users with the email provided
        List<User> users = new ArrayList<>();
        users.add(userReactiveRepository.findByEmailWithRole(email).block());

        Predicate<User> isNotValid = User::isValid;

        // users.removeIf(isNotValid);
        MyUserDetails myUserDetails = null;
        myUserDetails = new MyUserDetails(users.get(0));

        return myUserDetails;
    }

}
