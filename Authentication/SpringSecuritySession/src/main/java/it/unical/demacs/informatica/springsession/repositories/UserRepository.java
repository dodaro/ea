package it.unical.demacs.informatica.springsession.repositories;

import it.unical.demacs.informatica.springsession.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
