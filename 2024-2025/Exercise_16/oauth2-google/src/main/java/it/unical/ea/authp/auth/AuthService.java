package it.unical.ea.authp.auth;

import it.unical.ea.authp.entities.User;
import it.unical.ea.authp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;

    /**
     * Crea un OAuth2User con le autorità appropriate basate sui ruoli dell'utente
     *
     * @param attributes Attributi dell'utente OAuth2
     * @param email Email dell'utente
     * @param nameAttributeKey Nome dell'attributo da utilizzare come username
     * @return OAuth2User con le autorità corrette
     */
    public OAuth2User createOAuth2UserWithAuthorities(Map<String, Object> attributes, String email, String nameAttributeKey) {
        // Crea o recupera l'utente dal database
        User user = userService.getOrCreateUser(email);
        log.info("Utente trovato/creato nel database: {} con ruoli: {}", user.getEmail(), user.getRoles());

        // Converti i ruoli dell'utente in SimpleGrantedAuthority
        Collection<GrantedAuthority> authorities = convertRolesToAuthorities(user.getRoles());

        log.info("Autorità generate per l'utente: {}", authorities);
        return new DefaultOAuth2User(authorities, attributes, nameAttributeKey);
    }

    /**
     * Verifica se l'utente corrente ha il ruolo BASIC e lo aggiunge se necessario
     *
     * @param response Mappa opzionale per aggiungere informazioni alla risposta
     * @return true se il ruolo è stato aggiunto, false altrimenti
     */
    public boolean verificaEAggiungiRuoloBasic(Map<String, Object> response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            if (auth instanceof OAuth2AuthenticationToken &&
                    !auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_BASIC"))) {

                String email = auth.getName();
                log.info("Utente manca di ROLE_BASIC, aggiungiamolo: {}", email);

                // 1. Aggiorna l'utente nel database
                User user = userService.getOrCreateUser(email);
                if (!user.getRoles().contains("BASIC")) {
                    user.getRoles().add("BASIC");
                    userService.saveUser(user);
                    log.info("Ruolo BASIC aggiunto all'utente nel database: {}", email);
                }

                // 2. Aggiorna l'autenticazione corrente
                updateAuthentication((OAuth2AuthenticationToken) auth);

                // 3. Aggiorna la risposta se fornita
                updateResponse(response);

                return true;
            }
        }
        return false;
    }

    /**
     * Ottiene le informazioni sull'utente autenticato corrente
     *
     * @param response Mappa per aggiungere informazioni sulla risposta
     */
    public void aggiungiInfoUtente(Map<String, Object> response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            response.put("authenticated", true);
            response.put("user", auth.getName());
            response.put("authorities", auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));

            log.info("Utente autenticato: {} con autorità: {}", auth.getName(), auth.getAuthorities());
        } else {
            response.put("authenticated", false);
            log.info("Nessun utente autenticato");
        }
    }

    /**
     * Converte ruoli in autorità con il prefisso ROLE_
     *
     * @param roles Insieme di ruoli
     * @return Collezione di GrantedAuthority
     */
    private Collection<GrantedAuthority> convertRolesToAuthorities(Collection<String> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
            authorities.add(new SimpleGrantedAuthority(formattedRole));
            log.debug("Aggiunto ruolo: {}", formattedRole);
        }

        return authorities;
    }

    /**
     * Aggiorna l'autenticazione con autorità appropriate per l'utente
     *
     * @param oauthToken Token di autenticazione OAuth2
     */
    private void updateAuthentication(OAuth2AuthenticationToken oauthToken) {
        OAuth2User oAuth2User = oauthToken.getPrincipal();
        String email = oauthToken.getName();

        // Recupera l'utente con i ruoli aggiornati
        User user = userService.findByEmail(email).orElseThrow();

        // Crea nuove autorità basate sui ruoli dell'utente
        Collection<GrantedAuthority> updatedAuthorities = convertRolesToAuthorities(user.getRoles());

        // Crea un nuovo token di autenticazione
        OAuth2AuthenticationToken updatedAuth = new OAuth2AuthenticationToken(
                oAuth2User,
                updatedAuthorities,
                oauthToken.getAuthorizedClientRegistrationId()
        );

        SecurityContextHolder.getContext().setAuthentication(updatedAuth);
        log.info("Autorità aggiornate dinamicamente per l'utente: {}", updatedAuth.getAuthorities());
    }

    /**
     * Aggiorna la risposta HTTP con le autorità aggiornate
     *
     * @param response Mappa di risposta (può essere null)
     */
    private void updateResponse(Map<String, Object> response) {
        if (response != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            response.put("authorities", auth.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
            response.put("note", "Autorizzazioni aggiornate automaticamente con ruolo BASIC");
        }
    }
}
