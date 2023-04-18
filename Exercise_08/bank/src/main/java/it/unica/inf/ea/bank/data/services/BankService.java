package it.unica.inf.ea.bank.data.services;

import it.unica.inf.ea.bank.data.entities.Bank;
import it.unica.inf.ea.bank.data.entities.Teller;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BankService {

  Optional<List<Teller>> getAllTellersFromBankName(String bankName);

  List<Bank> countCosenzaBank();

  @Transactional
  void deleteAllCustomer(Long bankId);
}
