package it.unical.inf.ae.core;

import it.unical.inf.ae.data.dao.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
        "it.unical.inf.ae.core",      // Scansiona i servizi del core
        "it.unical.inf.ae.data"       // Scansiona i repository del data
})
@Import(JpaConfig.class)          // Importa la configurazione JPA
public class CoreConfig {
}
