package it.unica.inf.ea.bank.data.dao;

import it.unica.inf.ea.bank.data.entities.Customer;
import it.unica.inf.ea.bank.data.entities.Teller;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

  @Query("select c.tellers from CUSTOMER c where c.fiscalcode=: cf")
  List<Teller> getContract(String cf);

  default Specification<Customer> theLastFilter(Integer age) {
    return (Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
      LocalDate date = LocalDate.now().minus(age, ChronoUnit.YEARS);
      Predicate pred = criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), date);

      return criteriaQuery.where(pred).distinct(true).getRestriction();
    };
  }
}
