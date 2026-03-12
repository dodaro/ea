package it.unical.inf.ea.uniexample.dao;

import it.unical.inf.ea.uniexample.entities.other.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {}
