package it.unical.inf.ea.uniprjms.auth.api;

import it.unical.inf.ea.uniprjms.auth.dto.AuthRequest;
import it.unical.inf.ea.uniprjms.auth.dto.AuthResponse;
import it.unical.inf.ea.uniprjms.auth.dto.RegisterRequest;
import it.unical.inf.ea.uniprjms.auth.service.UserService;
import it.unical.inf.ea.uniprjms.shared.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        if (!jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.badRequest().build();
        }

        String username = jwtService.extractUserClaims(refreshToken).getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String newToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(newToken, refreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request.getEmail(), request.getPassword(), request.getRoles());
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
