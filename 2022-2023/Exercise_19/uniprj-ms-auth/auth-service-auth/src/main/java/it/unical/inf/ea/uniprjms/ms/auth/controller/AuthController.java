package it.unical.inf.ea.uniprjms.ms.auth.controller;

import it.unical.inf.ea.uniprjms.ms.auth.dto.AuthResponse;
import it.unical.inf.ea.uniprjms.ms.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/auth-api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(HttpServletRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
