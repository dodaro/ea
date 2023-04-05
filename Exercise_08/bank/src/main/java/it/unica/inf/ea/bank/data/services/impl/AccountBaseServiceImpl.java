package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.AccountBaseDao;
import it.unica.inf.ea.bank.data.services.AccountBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBaseServiceImpl implements AccountBaseService {

  @Autowired
  private AccountBaseDao accountBaseDao;

}
