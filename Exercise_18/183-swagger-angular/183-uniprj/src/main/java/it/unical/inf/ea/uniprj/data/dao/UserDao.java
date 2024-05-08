package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.entities.CourseMaterial;
import it.unical.inf.ea.uniprj.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao  extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
