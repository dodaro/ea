package it.unical.inf.ea.uniprjms.student.config.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class UserAuditorAware implements AuditorAware<Long> {

  private static final long AUTH_CODE = 1_000_001L;
  @Override
  public Optional<Long> getCurrentAuditor() {
    // logica per ricavare l'utente loggato (SpringSecurity)
    return Optional.of(AUTH_CODE);
  }
}
