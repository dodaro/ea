package it.unical.inf.ea.bank.data.services.impl;

import it.unical.inf.ea.bank.data.services.TellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TellerServiceImpl implements TellerService {

  // @Autowired
  private TellerService tellerService;
}
