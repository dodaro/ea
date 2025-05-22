package it.unical.inf.ae.data.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "it.unical.inf.ae.data.repository")
@EntityScan(basePackages = "it.unical.inf.ae.shared.entity")
@EnableJpaAuditing
public class JpaConfig {
}