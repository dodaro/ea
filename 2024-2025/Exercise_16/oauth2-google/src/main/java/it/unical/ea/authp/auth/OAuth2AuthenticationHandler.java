package it.unical.ea.authp.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Authentication Success Handler invocato per: {}", authentication.getName());

        // Verifica e aggiunge il ruolo BASIC se necessario
        boolean ruoloAggiunto = authService.verificaEAggiungiRuoloBasic(null);

        if (ruoloAggiunto) {
            log.info("Ruolo BASIC aggiunto all'utente durante la fase di login: {}", authentication.getName());
        } else {
            log.info("Nessuna modifica necessaria ai ruoli dell'utente: {}", authentication.getName());
        }

        // Continua con il normale flusso di autenticazione
        super.onAuthenticationSuccess(request, response, authentication);
    }


    /**
     * Crea un filtro invocabile  ad ogni richiesta
     */
    public OncePerRequestFilter createAfterFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                if (auth instanceof OAuth2AuthenticationToken) {
                    log.debug("Elaborazione utente autenticato: {}", auth.getName());

                    /*
                    // Verifica e aggiunge il ruolo BASIC se necessario
                    boolean ruoloAggiunto = authService.verificaEAggiungiRuoloBasic(null);

                    if (ruoloAggiunto) {
                        log.info("Ruolo BASIC aggiunto all'utente nel filtro: {}", auth.getName());
                    }
                    */
                }

                filterChain.doFilter(request, response);
            }
        };
    }
}
