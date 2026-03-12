package it.unical.ea.authp.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/keycloak";
    }

    @GetMapping("/user")
    @ResponseBody
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OidcUser user) {
        Map<String, Object> userInfo = new HashMap<>();
        if (user != null) {
            userInfo.put("name", user.getName());
            userInfo.put("email", user.getEmail());
            userInfo.put("authorities", user.getAuthorities());
        }
        return userInfo;
    }
}