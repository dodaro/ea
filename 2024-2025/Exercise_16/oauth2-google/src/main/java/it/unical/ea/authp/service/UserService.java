package it.unical.ea.authp.service;

import it.unical.ea.authp.dao.UserDao;
import it.unical.ea.authp.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserDao userRepository;

  @Transactional
  public User getOrCreateUser(String email) {
    log.info("Cercando utente con email: {}", email);

    Optional<User> existingUser = userRepository.findByEmail(email);

    if (existingUser.isPresent()) {
      User user = existingUser.get();
      log.info("Utente esistente trovato: {} con ruoli: {}", email, user.getRoles());
      return user;
    } else {
      log.info("Utente non trovato, creando nuovo utente con email: {}", email);

      User newUser = new User();
      newUser.setEmail(email);
      newUser.setActive(true);

      // Imposta il ruolo BASIC di default per i nuovi utenti
      // Nota: Salviamo "BASIC" senza il prefisso ROLE_ per coerenza con il modello dati
      HashSet<String> roles = new HashSet<>();
      roles.add("BASIC");
      newUser.setRoles(roles);

      User savedUser = userRepository.save(newUser);
      log.info("Nuovo utente salvato nel DB: {} con ID: {} e ruoli: {}",
              savedUser.getEmail(), savedUser.getId(), savedUser.getRoles());

      return savedUser;
    }
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }
}