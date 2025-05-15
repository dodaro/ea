package it.unical.ea.authp;

import it.unical.ea.authp.dao.ProductDao;
import it.unical.ea.authp.dao.UserDao;
import it.unical.ea.authp.entities.Product;
import it.unical.ea.authp.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class Auth3Application {

    public static void main(String[] args) {
        SpringApplication.run(Auth3Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserDao userDao, ProductDao productDao, PasswordEncoder encoder) {
        return args -> {
            // Utenti
            if (userDao.findByEmail("admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@example.com");
                admin.setPassword(encoder.encode("admin"));
                admin.setActive(true);
                admin.setDeleted(false);
                admin.setRoles(Set.of("ADMIN"));
                userDao.save(admin);
            }

            if (userDao.findByEmail("user@example.com").isEmpty()) {
                User user = new User();
                user.setEmail("user@example.com");
                user.setPassword(encoder.encode("password"));
                user.setActive(true);
                user.setDeleted(false);
                user.setRoles(Set.of("USER"));
                userDao.save(user);
            }

            // Prodotti
            if (productDao.count() == 0) {
                productDao.save(new Product("Laptop", 999.99));
                productDao.save(new Product("Smartphone", 599.49));
                productDao.save(new Product("Monitor", 199.00));
            }
        };
    }

}
