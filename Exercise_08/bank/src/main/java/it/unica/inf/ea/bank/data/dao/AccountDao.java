package it.unica.inf.ea.bank.data.dao;

import it.unica.inf.ea.bank.data.entities.Account;
import it.unica.inf.ea.bank.data.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

  @Query("from Account a where a.credential=: cred")
  List<Account> getAllBy(@Param("cred") Credential credential);

  List<Account> findAllByCredential(Credential credential);

  List<Account> findAllByCreationDateBetween(LocalDate from, LocalDate to);

}
