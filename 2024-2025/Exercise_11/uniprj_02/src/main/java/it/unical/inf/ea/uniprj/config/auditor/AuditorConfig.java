package it.unical.inf.ea.uniprj.config.auditor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.Random;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorConfig {
  /*
    Necessaria per definire il @CreatedBy @LastModifiedBy
  */

  @Bean
  public AuditorAware<Long> auditorProvider()
  {
    return new UserAuditorAware();
  }

}
