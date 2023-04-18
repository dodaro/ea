package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.AccountDao;
import it.unica.inf.ea.bank.data.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountDao accountDao;
}
