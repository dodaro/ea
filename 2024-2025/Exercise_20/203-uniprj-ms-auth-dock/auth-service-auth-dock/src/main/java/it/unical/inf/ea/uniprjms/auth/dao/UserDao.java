package it.unical.inf.ea.uniprjms.auth.dao;

import it.unical.inf.ea.uniprjms.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
