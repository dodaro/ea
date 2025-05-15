package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.dao.AccountDao;
import it.unical.inf.ea.bank.data.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  // @Autowired
  private AccountDao accountDao;
}
