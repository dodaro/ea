package it.unical.demacs.informatica.loginpassword.controllers;

import it.unical.demacs.informatica.loginpassword.domain.UserAccount;
import it.unical.demacs.informatica.loginpassword.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path="/register")
    @ResponseStatus(HttpStatus.OK)
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
    public String getUser(@PathVariable("username") String username, Authentication authentication) {
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
