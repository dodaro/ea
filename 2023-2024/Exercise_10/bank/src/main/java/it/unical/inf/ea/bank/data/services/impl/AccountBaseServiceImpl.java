package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.dao.AccountBaseDao;
import it.unical.inf.ea.bank.data.services.AccountBaseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountBaseServiceImpl implements AccountBaseService {

  // @Autowired
  private final AccountBaseDao accountBaseDao;

}
