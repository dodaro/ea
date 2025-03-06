package it.unical.inf.ea.bank.data.dao;

import it.unical.inf.ea.bank.data.entities.AccountBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBaseDao extends JpaRepository<AccountBase, Long> {
}
