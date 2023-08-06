/*package org.sid.services.config;

import org.sid.services.auth.userdetails.MyUserDetailService;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class CustomAuthenticationProvider implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        // Implement your authentication logic here
        // Return Mono<Authentication> with the authenticated user or an error if authentication fails

        // Example code to authenticate a user
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Perform authentication logic, e.g., check the username and password against a database

        // Create a new Authentication object with the authenticated user
        UserDetails userDetails = new MyUserDetailService().findByUsername(username).block();


        Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        return Mono.just(authenticatedUser);
        // Authentication failed

    }
}


 */
