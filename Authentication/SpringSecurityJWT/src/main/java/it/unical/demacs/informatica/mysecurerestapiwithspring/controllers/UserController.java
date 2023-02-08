package it.unical.demacs.informatica.mysecurerestapiwithspring.controllers;

import com.nimbusds.jose.JOSEException;
import it.unical.demacs.informatica.mysecurerestapiwithspring.domain.UserAccount;
import it.unical.demacs.informatica.mysecurerestapiwithspring.repositories.UserRepository;
import it.unical.demacs.informatica.mysecurerestapiwithspring.security.TokenStore;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public void authenticate(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) throws JOSEException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = TokenStore.getInstance().createToken(Map.of("username",  username));
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    @PostMapping(path="/register")
    public ResponseEntity<String> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        if("admin".equals(username) || userRepository.findByUsername(username) != null)
            return new ResponseEntity<>("existing username", HttpStatus.CONFLICT);
        UserAccount userAccount = new UserAccount(username, passwordEncoder.encode(password));
        userRepository.save(userAccount);
        return new ResponseEntity<>("registered", HttpStatus.OK);
    }

    @GetMapping(path="/users/{username}")
    @PreAuthorize("#username.equals(authentication.principal.getUsername()) or hasRole('ADMIN')")
    public String getUser(@PathVariable("username") String username) {
        UserAccount user = userRepository.findByUsername(username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getUsername());
        return jsonObject.toString();
    }

    @GetMapping(path="/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<UserAccount> users() {
        return userRepository.findAll();
    }

}
