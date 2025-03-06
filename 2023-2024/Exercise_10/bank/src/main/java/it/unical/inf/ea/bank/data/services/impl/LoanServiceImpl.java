package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.dao.LoanDao;
import it.unical.inf.ea.bank.data.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

  // @Autowired
  private LoanDao loanDao;
}
