package it.unical.ea.authp.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AuthService authService;
    private final DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth2 authentication flow started");

        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        log.info("Email ricevuta da OAuth2: {}", email);

        if (email == null) {
            log.error("Email non trovata negli attributi OAuth2");
            throw new OAuth2AuthenticationException("Email required");
        }

        try {
            // Utilizza AuthService per creare un OAuth2User con le autorità corrette
            Map<String, Object> attributes = oAuth2User.getAttributes();
            return authService.createOAuth2UserWithAuthorities(attributes, email, "email");
        } catch (Exception e) {
            log.error("Errore durante il recupero/creazione dell'utente", e);
            throw new OAuth2AuthenticationException(e.getMessage());
        }
    }
}
