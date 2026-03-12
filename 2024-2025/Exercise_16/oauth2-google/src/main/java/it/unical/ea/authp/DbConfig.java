package it.unical.ea.authp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DbConfig {

    private final JdbcTemplate jdbcTemplate;

    @EventListener(ContextRefreshedEvent.class)
    public void checkDatabaseTables() {
        log.info("Verifica delle tabelle del database dopo l'inizializzazione del contesto");
        try {
            // Verifica se la tabella user esiste e contiene record
            Integer userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", Integer.class);
            log.info("Numero di utenti nel database: {}", userCount);
            
            // Verifica se la tabella user_roles esiste e contiene record
            Integer rolesCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_roles", Integer.class);
            log.info("Numero di ruoli nel database: {}", rolesCount);
        } catch (Exception e) {
            log.error("Errore durante la verifica delle tabelle del database", e);
        }
    }
}