package it.unical.demacs.informatica.springsecurityopenidconnect.repositories;

import it.unical.demacs.informatica.springsecurityopenidconnect.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
