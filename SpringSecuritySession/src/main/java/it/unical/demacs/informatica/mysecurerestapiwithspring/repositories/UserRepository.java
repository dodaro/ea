package it.unical.demacs.informatica.mysecurerestapiwithspring.repositories;

import it.unical.demacs.informatica.mysecurerestapiwithspring.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
