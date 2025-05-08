package it.unical.ea.authp.api;

import it.unical.ea.authp.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final AuthService authService;

    /**
     * Endpoint principale che mostra la dashboard dopo l'autenticazione
     */
    @GetMapping("/")
    @ResponseBody
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        response.put("message", "Benvenuto nel Sistema OAuth2 Demo");
        response.put("user", auth.getName());
        response.put("endpoints", new String[]{"/info", "/basic", "/admin"});

        log.info("Accesso alla home page - Utente: {}", auth.getName());

        return response;
    }

    /**
     * Endpoint informativo accessibile a tutti
     */
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Informazioni pubbliche accessibili a tutti");

        // Aggiunge le informazioni sull'utente (se autenticato)
        authService.aggiungiInfoUtente(response);

        // Verifica e aggiunge il ruolo BASIC se necessario
        authService.verificaEAggiungiRuoloBasic(response);

        return response;
    }

    /**
     * Endpoint accessibile solo agli utenti con ruolo BASIC
     */
    @GetMapping("/basic")
    public Map<String, Object> getBasicInfo() {
        Map<String, Object> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.info("Accesso all'endpoint /basic - Utente: {}", auth.getName());

        response.put("message", "Contenuto accessibile agli utenti con ruolo BASIC");

        // Aggiunge le informazioni sull'utente
        authService.aggiungiInfoUtente(response);

        return response;
    }

    /**
     * Endpoint accessibile solo agli utenti con ruolo ADMIN
     */
    @GetMapping("/admin")
    public Map<String, Object> getAdminInfo() {
        Map<String, Object> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.info("Accesso all'endpoint /admin - Utente: {}", auth.getName());

        response.put("message", "Contenuto riservato agli amministratori");

        // Aggiunge le informazioni sull'utente
        authService.aggiungiInfoUtente(response);

        return response;
    }
}