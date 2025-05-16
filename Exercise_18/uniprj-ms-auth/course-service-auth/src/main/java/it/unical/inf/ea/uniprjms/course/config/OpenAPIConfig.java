package it.unical.inf.ea.uniprjms.course.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI courseServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Course Service API")
                        .description("API per la gestione dei corsi")
                        .version("1.0")
                        .contact(new Contact()
                                .name("UniPrj")
                                .email("info@uniprj.it")));
    }
}