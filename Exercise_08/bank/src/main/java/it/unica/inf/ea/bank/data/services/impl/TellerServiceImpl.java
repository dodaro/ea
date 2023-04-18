package it.unica.inf.ea.bank.data.services.impl;

import it.unica.inf.ea.bank.data.services.TellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TellerServiceImpl implements TellerService {

  @Autowired
  private TellerService tellerService;
}
