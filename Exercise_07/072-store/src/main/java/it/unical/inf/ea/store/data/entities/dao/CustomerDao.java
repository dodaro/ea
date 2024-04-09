package it.unical.inf.ea.store.data.entities.dao;

import it.unical.inf.ea.store.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

    /**
     * fatti in aula
     */
    List<Customer> findAllByLastname(String lastname);

    List<Customer> findAllByLastnameAndAndFirstname(String a, String b);

    List<Customer> findAllByBirthdateBetween(LocalDate d1, LocalDate d2);

    List<Customer> findAllByLastnameLike(String a);

    List<Customer> findAllByLastnameLikeOrBirthdateBetweenAndFiscalCodeNotNullAndFirstnameStartingWithOrderByFirstname();




}
