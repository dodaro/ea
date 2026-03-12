package it.unical.ea.authp.auth.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Set<String> roles;
}