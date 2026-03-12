package it.unical.ea.authp;

import it.unical.ea.authp.dao.UserDao;
import it.unical.ea.authp.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class OAuth2Google {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Google.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserDao userRepository) {
        return args -> {
            // Crea un utente admin di test se non esiste già
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                User adminUser = new User();
                adminUser.setEmail("admin@example.com");
                adminUser.setActive(true);

                Set<String> roles = new HashSet<>();
                roles.add("ADMIN");
                roles.add("BASIC");
                adminUser.setRoles(roles);

                userRepository.save(adminUser);
                log.info("Admin user created: admin@example.com con ruoli: {}", adminUser.getRoles());
            }

            // Crea un utente basic di test se non esiste già
            if (userRepository.findByEmail("user@example.com").isEmpty()) {
                User basicUser = new User();
                basicUser.setEmail("user@example.com");
                basicUser.setActive(true);

                Set<String> roles = new HashSet<>();
                roles.add("BASIC");
                basicUser.setRoles(roles);

                userRepository.save(basicUser);
                log.info("Basic user created: user@example.com con ruoli: {}", basicUser.getRoles());
            }

            // Prova a trovare lo specifico utente che potrebbe aver avuto problemi
            userRepository.findByEmail("francesco.scalzo@unical.it").ifPresent(user -> {
                log.info("Utente Francesco trovato nel DB con ID: {} e ruoli: {}", user.getId(), user.getRoles());

                // Se l'utente non ha ruoli, aggiungiamo il ruolo BASIC
                if (user.getRoles() == null || user.getRoles().isEmpty()) {
                    log.info("L'utente Francesco non ha ruoli, aggiungo BASIC");
                    Set<String> roles = new HashSet<>();
                    roles.add("BASIC");
                    user.setRoles(roles);
                    userRepository.save(user);
                    log.info("Ruolo BASIC aggiunto a Francesco, ruoli aggiornati: {}", user.getRoles());
                }
            });

            // Mostra tutti gli utenti all'avvio
            log.info("Utenti nel database:");
            userRepository.findAll().forEach(user ->
                    log.info("Utente: {}, ruoli: {}", user.getEmail(), user.getRoles())
            );
        };
    }
}