package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.BankDao;
import it.unica.inf.ea.bank.data.dao.CustomerDao;
import it.unica.inf.ea.bank.data.entities.Bank;
import it.unica.inf.ea.bank.data.entities.Customer;
import it.unica.inf.ea.bank.data.entities.Teller;
import it.unica.inf.ea.bank.data.services.BankService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

  @Autowired
  private BankDao bankDao;

  @Autowired
  private CustomerDao customerDao;

  @Override
  public Optional<List<Teller>> getAllTellersFromBankName(String bankName) {
    Bank bank = bankDao.findByName(bankName);
    if (bank != null)
      return Optional.of(bank.getTellers());
    return Optional.empty();
  }

  @Override
  public List<Bank> countCosenzaBank() {
    String cosenzaLocation = "Cosenza";
    Integer numCosenzaBank = bankDao.countByLocation(cosenzaLocation);
    List<Bank> banks = bankDao.findAllByLocation(cosenzaLocation);

    for (Bank bank : banks) {
      bank.numCosenzaBank = numCosenzaBank;
    }
    return banks;
  }

  @Override
  @Transactional
  public void deleteAllCustomer(Long bankId) {
    Bank bank = bankDao.findById(bankId).orElseThrow(() -> new RuntimeException("ERROR"));
    List<Customer> customers = bank.getCustomers();
    for (Customer customer : customers) {
      customerDao.delete(customer);
    }
  }

}
