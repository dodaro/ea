package it.unical.inf.ae.core;

import it.unical.inf.ae.data.repository.JpaConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "it.unical.inf.ae.core",      // Scansiona i servizi del core
        "it.unical.inf.ae.data"       // Scansiona i repository del data
})
@Import(JpaConfig.class)          // Importa la configurazione JPA
public class CoreConfig {
}
