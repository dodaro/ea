package it.unical.inf.ea.uniprjms.ms.auth.service;

import it.unical.inf.ea.uniprjms.ms.auth.conf.JwtUtil;
import it.unical.inf.ea.uniprjms.ms.auth.dto.AuthResponse;
import it.unical.inf.ea.uniprjms.ms.auth.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static it.unical.inf.ea.uniprjms.ms.auth.conf.JwtUtil.BASIC_TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(HttpServletRequest request) {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        String headerToken = StringUtils.delete(authorizationHeader, BASIC_TOKEN_PREFIX).trim();
        String username = JwtUtil.decodedBase64(headerToken)[0];
        String password = JwtUtil.decodedBase64(headerToken)[1];

        log.info("Username: " + username + " Password: " + password);

        User user = userService.findByEmail(username).orElseThrow(()->new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {

            List<String> roles = Arrays.stream(user.getRoles().split(",")) //
                                                   .map(role -> "ROLE_" + role)// oppure salvare "ROLE_" nel db
                                                   .collect(Collectors.toList());

            String accessToken = JwtUtil.createAccessToken(user.getEmail(), request.getRequestURL().toString(), roles);
            String refreshToken = JwtUtil.createRefreshToken(user.getEmail());
            return new AuthResponse(accessToken, refreshToken);
        }

        throw new RuntimeException("Bad credential");
    }
}