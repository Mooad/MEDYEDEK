package org.sid.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"org.sid"})
@EnableR2dbcRepositories
public class MedyedekAuthApplication extends SpringBootServletInitializer {



	public static void main(String[] args) {
		System.out.println("MEDYEDEK auth IS STARTED");
		SpringApplication.run(MedyedekAuthApplication.class, args);
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MedyedekAuthApplication.class);
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
