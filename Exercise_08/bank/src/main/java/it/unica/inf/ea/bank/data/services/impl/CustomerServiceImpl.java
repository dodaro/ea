package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.CustomerDao;
import it.unica.inf.ea.bank.data.entities.Customer;
import it.unica.inf.ea.bank.data.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerDao customerDao;

  @Override
  public List<Customer> getByAge(Integer age) {
    return customerDao.findAll(customerDao.theLastFilter(age));
  }
}
