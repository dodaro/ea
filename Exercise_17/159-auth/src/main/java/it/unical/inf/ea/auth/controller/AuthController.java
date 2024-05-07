package it.unical.inf.ea.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unical.inf.ea.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                Map<String, String> tokenMap = userService.refreshToken(authorizationHeader, request.getRequestURL().toString());
                response.addHeader("access_token", tokenMap.get("access_token"));
                response.addHeader("refresh_token", tokenMap.get("refresh_token"));
            }
            catch (Exception e) {
                log.error(String.format("Error refresh token: %s", authorizationHeader), e);
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> signIn(jakarta.servlet.http.HttpServletResponse response)  {

        try {

            String uname = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
            log.info("Principal/Username obtained from SecurityContextHolder: " + uname);
            log.info("Is Authenticated? : " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
    
            String msg = "OK " + uname + " ! You have been Logged In!";
            String token = ""; //JwtUtil.generateJWTUserToken(uname);
            log.info("Generated JWT Token: " + token);

//            response.addHeader(SecurityConstants.AUTH_HEADER, SecurityConstants.BEARER_TOKEN_PREFIX + token);
            return new ResponseEntity<>(msg, HttpStatus.OK);    

        } catch (Exception ex) {
            log.info("Exception error: " + ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

/*
    @PostMapping(value = "/auth/signup")
    public ResponseEntity<String> signUp() {

        String msg = "Ok! You have been Registered! - Now you can Login!";

        System.out.println(msg);
        try {
        
            return new ResponseEntity<>(msg, HttpStatus.OK);    

        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    */
}