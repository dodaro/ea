package it.unical.demacs.informatica.mysecurerestapiwithspring.repositories;

import it.unical.demacs.informatica.mysecurerestapiwithspring.domain.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
