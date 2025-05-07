package it.unical.ea.authp.service;

import it.unical.ea.authp.dao.UserDao;
import it.unical.ea.authp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDao userDao;
  private final PasswordEncoder passwordEncoder;

  public User registerUser(String email, String rawPassword, Set<String> roles) {
    if (userDao.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("Email già registrata");
    }

    User user = new User();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(rawPassword));
    user.setRoles(roles);
    user.setActive(false); // oppure true, se vuoi attivarlo subito
    user.setDeleted(false);

    return userDao.save(user);
  }

  public User findByEmail(String email) {
    return userDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public void activateUser(Long userId) {
    userDao.findById(userId).ifPresent(user -> {
      user.setActive(true);
      userDao.save(user);
    });
  }

  public void deactivateUser(Long userId) {
    userDao.findById(userId).ifPresent(user -> {
      user.setActive(false);
      user.setDeleted(true);
      userDao.save(user);
    });
  }

  public List<User> getAllActiveUsers() {
    return userDao.findAll().stream()
            .filter(user -> user.isActive() && !user.isDeleted())
            .toList();
  }

  public User register(String email, String password, Set<String> roles) {
    if (userDao.findByEmail(email).isPresent()) {
      throw new IllegalArgumentException("Email già registrata.");
    }

    User user = new User();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setRoles(roles);
    user.setActive(true);     // oppure false se vuoi validazione manuale
    user.setDeleted(false);

    return userDao.save(user);
  }
}
