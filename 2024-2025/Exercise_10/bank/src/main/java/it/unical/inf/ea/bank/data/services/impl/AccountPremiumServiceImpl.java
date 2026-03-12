package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.dao.AccountPremiumDao;
import it.unical.inf.ea.bank.data.services.AccountPremiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountPremiumServiceImpl implements AccountPremiumService {

  // @Autowired
  private AccountPremiumDao accountPremiumDao;
}
