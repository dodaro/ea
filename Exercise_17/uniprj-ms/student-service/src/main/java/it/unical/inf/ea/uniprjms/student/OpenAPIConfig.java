package it.unical.inf.ea.uniprjms.student;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI studentServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Service API")
                        .description("API per la gestione degli studenti")
                        .version("1.0")
                        .contact(new Contact()
                                .name("UniPrj")
                                .email("info@uniprj.it")));
    }
}