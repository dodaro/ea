package it.unica.inf.ea.bank.data.dao;

import it.unica.inf.ea.bank.data.entities.Teller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerDao extends JpaRepository<Teller, Long> {
}
