package it.unical.ea.authp.api;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/debug")
public class DebugController {

    @GetMapping("/debug-auth")
    @ResponseBody
    public Map<String, Object> debugAuth(Authentication auth, HttpSession session) {
        Map<String, Object> debug = new HashMap<>();
        debug.put("session_id", session.getId());
        debug.put("session_creation_time", new Date(session.getCreationTime()));
        debug.put("authenticated", auth != null && auth.isAuthenticated());
        if (auth != null) {
            debug.put("auth_type", auth.getClass().getName());
            debug.put("name", auth.getName());
            debug.put("authorities", auth.getAuthorities());
        }
        return debug;
    }

    @GetMapping
    @ResponseBody
    public Map<String, Object> debugInfo(Authentication authentication) {
        Map<String, Object> debugInfo = new HashMap<>();

        if (authentication == null) {
            debugInfo.put("authenticated", false);
            return debugInfo;
        }

        debugInfo.put("authenticated", true);
        debugInfo.put("name", authentication.getName());
        debugInfo.put("principal_type", authentication.getPrincipal().getClass().getName());
        debugInfo.put("authorities", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            debugInfo.put("client_registration", oauth2Token.getAuthorizedClientRegistrationId());

            if (oauth2Token.getPrincipal() instanceof OidcUser) {
                OidcUser oidcUser = (OidcUser) oauth2Token.getPrincipal();

                // Crea una mappa con tutte le informazioni dell'utente
                debugInfo.put("user_attributes", oidcUser.getAttributes());

                // Estrai informazioni dal token ID
                OidcIdToken idToken = oidcUser.getIdToken();
                if (idToken != null) {
                    debugInfo.put("token_claims", idToken.getClaims());
                }

                // Controlla specificamente le chiavi di claim che contengono ruoli
                Map<String, Object> allClaims = oidcUser.getClaims();
                debugInfo.put("all_claims", allClaims);

                if (allClaims.containsKey("realm_access")) {
                    debugInfo.put("realm_access", allClaims.get("realm_access"));
                }

                if (allClaims.containsKey("resource_access")) {
                    debugInfo.put("resource_access", allClaims.get("resource_access"));
                }

                if (allClaims.containsKey("roles")) {
                    debugInfo.put("roles_claim", allClaims.get("roles"));
                }
            }
        }

        return debugInfo;
    }
}
