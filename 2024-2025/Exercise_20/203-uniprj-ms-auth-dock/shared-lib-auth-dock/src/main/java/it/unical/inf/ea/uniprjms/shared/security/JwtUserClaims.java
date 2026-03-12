package it.unical.inf.ea.uniprjms.shared.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * DTO che contiene username e ruoli estratti dal token JWT.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserClaims {
    private String username;
    private List<String> authorities;
}