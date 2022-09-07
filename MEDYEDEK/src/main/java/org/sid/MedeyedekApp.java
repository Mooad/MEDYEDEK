package org.sid;


import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.Collections;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableSwagger2
public class MedeyedekApp implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MedeyedekApp.class, args);
    }

    public void run(String... args) throws Exception {


        System.out.println("MEDYEDEK IS STARTED");


    }

    @Bean
    public Docket medyedekSwaggerConf() {
        return new Docket(DocumentationType.SWAGGER_2).select().build().apiInfo(new ApiInfo("MEDYEDEK API INFO", "All endpoints documentation and errors that are returned", "1.0", "", new springfox.documentation.service.Contact("MOAD FAJRI", "http://javabrains.io", "fajrimoad@gmail.com"), "@MEDYEDEK", "http://localhost:4200", Collections.emptyList()));

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
