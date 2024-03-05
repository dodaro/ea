package it.unical.demacs.informatica.userhandlerservice.repositories;

import it.unical.demacs.informatica.userhandlerservice.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    UserAccount findByEmail(String email);

    Iterable<UserAccount> findByConsentToContactByEmail(boolean value);
}
