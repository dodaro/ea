package it.unical.ea.authp.api;

import it.unical.ea.authp.dao.UserDao;
import it.unical.ea.authp.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
@Slf4j
public class DebugController {

    private final UserDao userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        log.info("Richiesta di visualizzazione di tutti gli utenti");
        return userRepository.findAll();
    }
    
    @GetMapping("/auth-info")
    public Map<String, Object> getAuthInfo() {
        Map<String, Object> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            response.put("isAuthenticated", auth.isAuthenticated());
            response.put("principal", auth.getPrincipal().toString());
            response.put("name", auth.getName());
            response.put("authorities", auth.getAuthorities());
            response.put("details", auth.getDetails());
            response.put("credentials", auth.getCredentials() != null ? "PROTECTED" : null);
        } else {
            response.put("isAuthenticated", false);
        }
        
        return response;
    }
}