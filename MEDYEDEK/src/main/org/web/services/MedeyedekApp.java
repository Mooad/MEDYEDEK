package org.web.services;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication(scanBasePackages = {"org.sid","org.web"},exclude = {SecurityAutoConfiguration.class })
@EnableR2dbcRepositories(basePackages = "org.sid.services")
@EnableReactiveMongoRepositories(basePackages = "org.sid.services.nosql.repositories")
public class MedeyedekApp extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        System.out.println("MEDYEDEK IS STARTED");
        SpringApplication.run(MedeyedekApp.class, args);
    }
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MedeyedekApp.class);
    }




    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("*");
        // The below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedOrigin("http://localhost:8081");
        configuration.addAllowedHeader("");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
