package it.unica.inf.ea.bank.data.dao;

import it.unica.inf.ea.bank.data.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDao extends JpaRepository<Loan, Long> {
}
