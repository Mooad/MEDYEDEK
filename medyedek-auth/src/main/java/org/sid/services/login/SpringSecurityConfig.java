package org.sid.services.login;


import lombok.RequiredArgsConstructor;
import org.sid.services.auth.jdbc.MyUserJDBCDetailService;
import org.sid.services.enumeration.Roles;
import org.sid.services.jwt.JwtAuthenticationEntryPoint;
import org.sid.services.jwt.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@EnableMethodSecurity()
public class SpringSecurityConfig {



    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private MyUserJDBCDetailService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)   throws Exception {


        http.csrf().disable().cors().and()
                .authorizeHttpRequests().requestMatchers("/**")
                .hasAnyAuthority(Roles.APPUSER.toString(),Roles.DONN.toString(),Roles.BENEF.toString()).and()
                .httpBasic().and()
                .authenticationProvider(authenticationProvider())
                //.addFilter(new CustomAuthenticationFilter(authManagerBuilder.getObject()))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();

    }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

}
