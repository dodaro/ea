package it.unical.demacs.informatica.springsecurityopenidconnect.controllers;

import it.unical.demacs.informatica.springsecurityopenidconnect.domain.UserAccount;
import it.unical.demacs.informatica.springsecurityopenidconnect.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) { this.userRepository = userRepository; }

    @GetMapping(path="/google_login")
    public ResponseEntity<String> register(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                                           @AuthenticationPrincipal OAuth2User oauth2User) {
        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());
        UserAccount userAccount = userRepository.findByUsername(oauth2User.getName());
        if(userAccount == null) {
            userAccount = new UserAccount(oauth2User.getName(),
                    oauth2User.getAttributes().get("email").toString(),
                    oauth2User.getAttributes().get("given_name").toString(),
                    oauth2User.getAttributes().get("family_name").toString());
            userRepository.save(userAccount);
        }
        return new ResponseEntity<>(userAccount.toString(), HttpStatus.OK);
    }

    @GetMapping(path="/users/{username}")
    @PreAuthorize("hasAuthority('SCOPE_openid') and #username.equals(authentication.name)")
    public String getUser(@PathVariable("username") String username) {
        UserAccount user = userRepository.findByUsername(username);
        return user.toString();
    }

    @GetMapping(path="/users")
    @PreAuthorize("hasAuthority('SCOPE_openid') and #oauth2User.getAttribute('email').equals('carmine.dodaro@unical.it')")
    public Iterable<UserAccount> users(@AuthenticationPrincipal OAuth2User oauth2User) {
        return userRepository.findAll();
    }
}
