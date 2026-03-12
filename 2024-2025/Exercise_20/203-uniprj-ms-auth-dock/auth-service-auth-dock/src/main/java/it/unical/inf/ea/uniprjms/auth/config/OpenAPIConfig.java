package it.unical.inf.ea.uniprjms.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public OpenAPI courseServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Auth Service API")
                        .description("API per la gestione dell'autenticazione")
                        .version("1.0")
                        .contact(new Contact()
                                .name("UniPrj")
                                .email("info@uniprj.it")));
    }
}