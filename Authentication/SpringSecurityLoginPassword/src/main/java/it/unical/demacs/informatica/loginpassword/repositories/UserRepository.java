package it.unical.demacs.informatica.loginpassword.repositories;

import it.unical.demacs.informatica.loginpassword.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
