package it.unical.inf.ea.bank.data.dao;

import it.unical.inf.ea.bank.data.entities.Account;
import it.unical.inf.ea.bank.data.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

  @Query("from Account a where a.credential=: cred")
  Optional<Account> getAllBy(@Param("cred") Credential credential);

  @Query("from Account a where a.credential.username=:username and a.credential.password=:passw")
  Optional<Account> getAllBy(@Param("username") String usern, @Param("passw")String pass);

  Optional<Account> findByCredential(Credential credential);

  Optional<Account> findByCredentialUsernameAnAndCredentialPassword(String username, String password);

  List<Account> findAllByCreationDateBetween(LocalDate from, LocalDate to);

}
