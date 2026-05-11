package it.unical.ea.authp.auth.dto;

import lombok.Data;

/**
 * Registrazione utente. NOTA: il ruolo NON e' nel DTO di proposito:
 * un client che potesse settarlo si auto-promuoverebbe ADMIN.
 * Il ruolo viene forzato a USER server-side in AuthController.register.
 */
@Data
public class RegisterRequest {
    private String email;
    private String password;
}