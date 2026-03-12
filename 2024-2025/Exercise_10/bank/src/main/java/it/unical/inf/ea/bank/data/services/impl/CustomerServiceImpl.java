package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.dao.CustomerDao;
import it.unical.inf.ea.bank.data.entities.Customer;
import it.unical.inf.ea.bank.data.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  //@Autowired
  private CustomerDao customerDao;

  @Override
  public List<Customer> getByAge(Integer age) {
    Specification<Customer> spec = customerDao.theLastFilter(age);
    return customerDao.findAll(spec);
  }

  @Override
  public List<Customer> getByAge2(Integer age) {
    return customerDao.findAllByBirthdateEqual(LocalDate.now().minus(age, ChronoUnit.YEARS));}
}
