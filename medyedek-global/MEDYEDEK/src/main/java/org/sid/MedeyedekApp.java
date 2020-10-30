package org.sid;



import org.modelmapper.ModelMapper;
import org.sid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.BeneficiaireRepository;
import repositories.TypepostRepository;
import repositories.donnateurRepository;
import repositories.postsRepository;


@EntityScan({"org.sid.entities"})
@EnableJpaRepositories({"repositories"})
@ComponentScan({"org.sid.entities","org.sid.config","org.sid.service","medyedek.service.mailconfirmation","medyedek.service.setting"})
@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class MedeyedekApp  implements CommandLineRunner {
	


	
	public static void main(String[] args) {
		SpringApplication.run(MedeyedekApp.class, args);
	}
	public void run(String... args) throws Exception {	
			  	   
		 		
		System.out.println("MEDYEDEK IS STARTED");
		
	
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
