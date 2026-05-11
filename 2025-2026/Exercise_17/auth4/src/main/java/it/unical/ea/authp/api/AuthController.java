package it.unical.ea.authp.api;

import io.jsonwebtoken.JwtException;
import it.unical.ea.authp.auth.JwtService;
import it.unical.ea.authp.auth.TokenBlacklistService;
import it.unical.ea.authp.auth.dto.AuthRequest;
import it.unical.ea.authp.auth.dto.AuthResponse;
import it.unical.ea.authp.auth.dto.RefreshRequest;
import it.unical.ea.authp.auth.dto.RegisterRequest;
import it.unical.ea.authp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenBlacklistService blacklistService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        String refresh = jwtService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, refresh));
    }

    /**
     * Refresh con rotation: invalida il vecchio refresh (jti -> blacklist) e ne emette uno nuovo.
     * Cosi' un refresh token rubato puo' essere usato una sola volta.
     */
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest body) {
        String refreshToken = body.getRefreshToken();
        if (refreshToken == null || !jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String oldJti = jwtService.extractJti(refreshToken);
            if (blacklistService.isRevoked(oldJti)) {
                return ResponseEntity.status(401).build();
            }
            blacklistService.revoke(oldJti);

            String username = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newAccess = jwtService.generateToken(userDetails);
            String newRefresh = jwtService.generateRefreshToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(newAccess, newRefresh));
        } catch (JwtException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Registrazione pubblica: ruolo forzato a USER lato server.
     * Il client NON puo' auto-assegnarsi ADMIN passando 'roles' nel body.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request.getEmail(), request.getPassword(), Set.of("USER"));
            return ResponseEntity.ok("Utente registrato con successo.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * Logout: revoca il token corrente (access).
     * Usabile anche per revocare un refresh passando quello.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing Bearer token");
        }
        try {
            String jti = jwtService.extractJti(authHeader.substring(7));
            blacklistService.revoke(jti);
            return ResponseEntity.ok("Logout effettuato.");
        } catch (JwtException ex) {
            return ResponseEntity.badRequest().body("Token non valido");
        }
    }
}
