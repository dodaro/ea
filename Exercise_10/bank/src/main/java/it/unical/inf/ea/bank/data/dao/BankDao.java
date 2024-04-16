package it.unical.inf.ea.bank.data.dao;

import it.unical.inf.ea.bank.data.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDao extends JpaRepository<Bank, Long> {

  Bank findByName(String name);

  Integer countByLocation(String location);

  List<Bank> findAllByLocation(String location);
}
