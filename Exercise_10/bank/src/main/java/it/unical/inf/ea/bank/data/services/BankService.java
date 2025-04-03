package it.unical.inf.ea.bank.data.services;

import it.unical.inf.ea.bank.data.entities.Bank;
import it.unical.inf.ea.bank.data.entities.Teller;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BankService {

  List<Teller> getAllTellersFromBankName(String bankName);

  List<Bank> countCosenzaBank();

  @Transactional
  void deleteAllCustomer(Long bankId);
}
