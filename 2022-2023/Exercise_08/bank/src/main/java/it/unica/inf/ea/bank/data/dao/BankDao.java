package it.unica.inf.ea.bank.data.dao;

import it.unica.inf.ea.bank.data.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankDao extends JpaRepository<Bank, Long> {

  Bank findByName(String name);

  Integer countByLocation(String location);

  List<Bank> findAllByLocation(String location);
}
