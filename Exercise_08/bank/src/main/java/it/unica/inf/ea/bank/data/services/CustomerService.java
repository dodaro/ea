package it.unica.inf.ea.bank.data.services;


import it.unica.inf.ea.bank.data.entities.Customer;

import java.util.List;

public interface CustomerService {
  List<Customer> getByAge(Integer age);

  List<Customer> getByAge2(Integer age);
}
