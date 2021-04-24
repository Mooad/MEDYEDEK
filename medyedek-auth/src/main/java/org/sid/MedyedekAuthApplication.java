package org.sid;

import org.sid.repositories.UtilisateurRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UtilisateurRepository.class)
@EntityScan({"org.sid.entities"})
@ComponentScan({"org.sid.entities","org.sid.repositories","org.sid.user","org.sid.login","org.sid.controller"})
public class MedyedekAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedyedekAuthApplication.class, args);
	}




}
