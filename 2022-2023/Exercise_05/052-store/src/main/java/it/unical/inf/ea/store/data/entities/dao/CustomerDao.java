package it.unical.inf.ea.store.data.entities.dao;

import it.unical.inf.ea.store.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
