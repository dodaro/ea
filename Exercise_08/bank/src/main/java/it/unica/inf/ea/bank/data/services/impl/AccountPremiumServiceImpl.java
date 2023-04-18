package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.dao.AccountPremiumDao;
import it.unica.inf.ea.bank.data.services.AccountPremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountPremiumServiceImpl implements AccountPremiumService {

  @Autowired
  private AccountPremiumDao accountPremiumDao;
}
