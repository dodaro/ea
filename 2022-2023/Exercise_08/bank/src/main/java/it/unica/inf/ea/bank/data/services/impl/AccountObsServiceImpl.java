package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.AccountObsDao;
import it.unica.inf.ea.bank.data.services.AccountObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountObsServiceImpl implements AccountObsService {

  @Autowired
  private AccountObsDao accountObsDao;

}
