package it.unical.inf.ea.step5_annotations.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Con @ComponentScan Spring scansiona automaticamente il package indicato
 * e registra come bean tutte le classi annotate con @Component, @Service,
 * @Repository, @Controller.
 *
 * Non serve piu' dichiarare i bean uno per uno (ne' in XML ne' con @Bean).
 */
@Configuration
@ComponentScan("it.unical.inf.ea.step5_annotations")
public class AppConfig {
}
