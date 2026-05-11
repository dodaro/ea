package it.unical.ea.authp.api;

import it.unical.ea.authp.entities.User;
import it.unical.ea.authp.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Endpoint pubblico: chiunque puo' registrarsi come USER.
    // Il body NON consente di specificare ruoli: ruolo forzato server-side.
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User saved = userService.registerUser(
                    request.getEmail(),
                    request.getPassword(),
                    Set.of("USER")
            );
            // L'utente nasce inattivo: attivazione manuale da admin
            return ResponseEntity.ok("Utente " + saved.getEmail() + " registrato (in attesa di attivazione).");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Data
    public static class RegisterRequest {
        private String email;
        private String password;
    }
}
