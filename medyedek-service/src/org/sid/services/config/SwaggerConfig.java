package org.sid.services.config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket medyedekSwaggerConf() {
        return new Docket(DocumentationType.SWAGGER_2).select().build()
                .apiInfo(new ApiInfo("MEDYEDEK API INFO"
                        , "All endpoints documentation and errors that are returned"
                        , "1.0"
                        , ""
                        , new springfox.documentation.service.Contact("MOAD FAJRI"
                        , "http://javabrains.io"
                        , "fajrimoad@gmail.com")
                        , "@MEDYEDEK", "http://localhost:4200", Collections.emptyList()));

    }

}
