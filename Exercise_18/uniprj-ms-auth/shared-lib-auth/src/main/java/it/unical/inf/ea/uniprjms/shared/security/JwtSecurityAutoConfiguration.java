package it.unical.inf.ea.uniprjms.shared.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Map;

/*
 * Configura automaticamente la sicurezza in ogni microservizio in base alle proprietà definite nei file YAML.
 */
@Configuration
@ConditionalOnProperty(name = "jwt.security.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JwtSecurityProperties.class)
@Import({JwtServiceConfiguration.class, CorsConfiguration.class})
public class JwtSecurityAutoConfiguration {

    private final JwtSecurityProperties securityProperties;
    private final JwtService jwtService;

    public JwtSecurityAutoConfiguration(JwtSecurityProperties securityProperties, JwtService jwtService) {
        this.securityProperties = securityProperties;
        this.jwtService = jwtService;
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, 
                                          JwtAuthFilter jwtAuthFilter, 
                                          CorsConfigurationSource corsConfigurationSource) throws Exception {
        
        // Se il servizio è contrassegnato come "open", permette tutti gli accessi
        if (securityProperties.isOpenService()) {
            return http
                    .cors(c -> c.configurationSource(corsConfigurationSource))
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                    .build();
        }
        
        // Altrimenti, configura la sicurezza in base alle rotte definite
        return http
                .cors(c -> c.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    // Configura le rotte pubbliche
                    if (securityProperties.getPublicPaths() != null && securityProperties.getPublicPaths().length > 0) {
                        auth.requestMatchers(securityProperties.getPublicPaths()).permitAll();
                    }

                    // Nella configurazione delle rotte protette, modifica per supportare anche i metodi HTTP
                    if (securityProperties.getProtectedRoutes() != null) {
                        for (Map.Entry<String, String[]> entry : securityProperties.getProtectedRoutes().entrySet()) {
                            String path = entry.getKey();
                            String[] roles = entry.getValue();

                            // Controlla se il path contiene indicazione del metodo HTTP
                            if (path.contains("/POST") || path.contains("/PUT") || path.contains("/DELETE")) {
                                String actualPath = path.split("/")[0];
                                String method = path.split("/")[1];

                                switch(method) {
                                    case "POST":
                                        auth.requestMatchers(HttpMethod.POST, actualPath).hasAnyAuthority(roles);
                                        break;
                                    case "PUT":
                                        auth.requestMatchers(HttpMethod.PUT, actualPath).hasAnyAuthority(roles);
                                        break;
                                    case "DELETE":
                                        auth.requestMatchers(HttpMethod.DELETE, actualPath).hasAnyAuthority(roles);
                                        break;
                                }
                            } else {
                                auth.requestMatchers(path).hasAnyAuthority(roles);
                            }
                        }
                    }
                    
                    // Per default, tutte le altre rotte richiedono autenticazione
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}