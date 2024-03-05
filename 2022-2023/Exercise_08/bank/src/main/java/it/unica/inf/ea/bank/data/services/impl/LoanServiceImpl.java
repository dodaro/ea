package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.LoanDao;
import it.unica.inf.ea.bank.data.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

  @Autowired
  private LoanDao loanDao;
}
