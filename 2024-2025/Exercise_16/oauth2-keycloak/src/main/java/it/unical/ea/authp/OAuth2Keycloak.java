package it.unical.ea.authp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OAuth2Keycloak {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Keycloak.class, args);
    }

}