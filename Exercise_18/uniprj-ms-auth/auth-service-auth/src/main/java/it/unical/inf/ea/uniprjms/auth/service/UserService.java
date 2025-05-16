package it.unical.inf.ea.uniprjms.auth.service;

import it.unical.inf.ea.uniprjms.auth.dao.UserDao;
import it.unical.inf.ea.uniprjms.auth.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password, Set<String> roles) {
        if (userDao.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(roles)
                .active(true)
                .deleted(false)
                .build();

        return userDao.save(user);
    }
}
