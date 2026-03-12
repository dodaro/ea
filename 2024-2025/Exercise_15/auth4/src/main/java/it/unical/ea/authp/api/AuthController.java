package it.unical.ea.authp.api;

import it.unical.ea.authp.auth.JwtService;
import it.unical.ea.authp.auth.dto.AuthRequest;
import it.unical.ea.authp.auth.dto.AuthResponse;
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

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

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

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody String refreshToken) {
        if (!jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.badRequest().build();
        }

        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String newToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(newToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request.getEmail(), request.getPassword(), request.getRoles());
            return ResponseEntity.ok("Utente registrato con successo.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
