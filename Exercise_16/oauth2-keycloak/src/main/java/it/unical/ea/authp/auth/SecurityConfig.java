package it.unical.ea.authp.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String ROLE_PREFIX = "ROLE_";

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    private String issuerUri;

    /**
     * Configura la catena dei filtri di sicurezza Spring Security.
     * Supporta contemporaneamente l’accesso via browser (web-based) e via API (token-based)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/welcome", "/debug-auth").permitAll()
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        .requestMatchers("/api/basic").hasRole("BASIC")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(oidcUserService())
                        )
                        .defaultSuccessUrl("/", true)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/api/welcome")
                )
                .build();
    }

    /**
     * Crea il decoder JWT basato sull'issuer di Keycloak.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    /**
     * Converte un token JWT in un oggetto Authentication, includendo ruoli realm e client.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(ROLE_PREFIX);

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new HashSet<>(Optional.ofNullable(grantedAuthoritiesConverter.convert(jwt)).orElse(Collections.emptyList()));
            authorities.addAll(extractRoles(jwt.getClaims()));
            return authorities;
        });

        return converter;
    }

    /**
     * Definisce il servizio personalizzato per caricare un OidcUser e arricchirlo con ruoli Keycloak.
     */
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        OidcUserService delegate = new OidcUserService();

        return userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);

            Set<GrantedAuthority> mappedAuthorities = new HashSet<>(oidcUser.getAuthorities());
            mappedAuthorities.addAll(extractRoles(oidcUser.getClaims()));

            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

    /**
     * Estrae i ruoli da realm_access e resource_access del token.
     */
    private Collection<GrantedAuthority> extractRoles(Map<String, Object> claims) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        extractRolesFromRealmAccess(claims, authorities);
        extractRolesFromResourceAccess(claims, authorities);
        return authorities;
    }

    /**
     * Estrae ruoli dal campo realm_access del token.
     */
    private void extractRolesFromRealmAccess(Map<String, Object> claims, Set<GrantedAuthority> authorities) {
        Map<String, Object> realmAccess = getMap(claims, "realm_access");
        if (realmAccess != null) {
            List<String> roles = getList(realmAccess, "roles");
            addRoles(roles, authorities);
        }
    }

    /**
     * Estrae ruoli dal campo resource_access del token, per tutti i client.
     */
    private void extractRolesFromResourceAccess(Map<String, Object> claims, Set<GrantedAuthority> authorities) {
        Map<String, Object> resourceAccess = getMap(claims, "resource_access");
        if (resourceAccess != null) {
            resourceAccess.values().forEach(clientAccess -> {
                if (clientAccess instanceof Map) {
                    List<String> roles = getList((Map<String, Object>) clientAccess, "roles");
                    addRoles(roles, authorities);
                }
            });
        }
    }

    /**
     * Aggiunge una lista di ruoli alle authorities, con prefisso ROLE_.
     */
    private void addRoles(List<String> roles, Set<GrantedAuthority> authorities) {
        if (roles != null) {
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getMap(Map<String, Object> claims, String key) {
        Object value = claims.get(key);
        return value instanceof Map ? (Map<String, Object>) value : null;
    }

    @SuppressWarnings("unchecked")
    private static List<String> getList(Map<String, Object> claims, String key) {
        Object value = claims.get(key);
        return value instanceof List ? (List<String>) value : null;
    }
}
