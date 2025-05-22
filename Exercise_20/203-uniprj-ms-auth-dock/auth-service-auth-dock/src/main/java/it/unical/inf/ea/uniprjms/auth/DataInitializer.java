package it.unical.inf.ea.uniprjms.auth;

import it.unical.inf.ea.uniprjms.auth.dao.UserDao;
import it.unical.inf.ea.uniprjms.auth.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Verifica se esistono già utenti nel database
        if (userDao.count() == 0) {
            // Crea un utente admin
            User admin = User.builder()
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("password"))
                    .roles(Set.of("ROLE_ADMIN", "ROLE_USER"))
                    .active(true)
                    .build();
            userDao.save(admin);

            // Crea un utente normale
            User user = User.builder()
                    .email("user@example.com")
                    .password(passwordEncoder.encode("password"))
                    .roles(Set.of("ROLE_USER"))
                    .active(true)
                    .build();
            userDao.save(user);
        }
    }
}