package it.unical.demacs.informatica.springjwt.repositories;

import it.unical.demacs.informatica.springjwt.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
