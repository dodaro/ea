package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.dao.AccountObsDao;
import it.unical.inf.ea.bank.data.services.AccountObsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountObsServiceImpl implements AccountObsService {

  // @Autowired
  private final AccountObsDao accountObsDao;

}
