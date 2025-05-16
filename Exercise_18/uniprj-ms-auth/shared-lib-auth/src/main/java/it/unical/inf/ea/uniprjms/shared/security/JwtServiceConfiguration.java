package it.unical.inf.ea.uniprjms.shared.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Configura l'istanza JwtService con i valori delle proprietà come chiave segreta e tempi di scadenza.
 */
@Configuration
public class JwtServiceConfiguration {

    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration:3600000}")
    private long jwtExpiration;
    
    @Value("${jwt.refresh.expiration:86400000}")
    private long refreshExpiration;

    @Bean
    public JwtService jwtService() {
        JwtService jwtService = new JwtService();
        jwtService.setJwtSecret(jwtSecret);
        jwtService.setJwtExpiration(jwtExpiration);
        jwtService.setRefreshExpiration(refreshExpiration);
        return jwtService;
    }
}