package it.unical.inf.ea.uniprj.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.Random;

public class UserAuditorAware implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    // logica per ricavare l'utente loggato (SpringSecurity)
    return Optional.of(new Random().nextLong(3));
  }
}
