package it.unical.demacs.informatica.userhandlerservice.controller;

import it.unical.demacs.informatica.userhandlerservice.model.UserAccount;
import it.unical.demacs.informatica.userhandlerservice.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:{server.port}")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/register")
    public ResponseEntity<String> register(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("consent") boolean consent) {
        if(userRepository.findByUsername(username) != null)
            return new ResponseEntity<>("existing username", HttpStatus.CONFLICT);
        if(userRepository.findByEmail(email) != null)
            return new ResponseEntity<>("existing email", HttpStatus.CONFLICT);
        UserAccount userAccount = new UserAccount(username, email, consent);
        userRepository.save(userAccount);
        return new ResponseEntity<>("registered", HttpStatus.OK);
    }

    @GetMapping(path="/users/{username}")
    public UserAccount getUser(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping(path="/users")
    public String users() {
        JSONObject response = new JSONObject();
        response.put("users", userRepository.findAll());
        return response.toString();
    }

    @GetMapping(path="/contactable_users")
    public String contactableUsers() {
        JSONObject response = new JSONObject();
        response.put("contactable_users", userRepository.findByConsentToContactByEmail(true));
        return response.toString();
    }
}
