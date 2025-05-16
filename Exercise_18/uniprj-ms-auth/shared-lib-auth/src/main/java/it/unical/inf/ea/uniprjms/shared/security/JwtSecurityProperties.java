package it.unical.inf.ea.uniprjms.shared.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/*
 * Definisce le proprietà configurabili (percorsi pubblici, rotte protette, flags) per la sicurezza di ogni servizio.
 */
@ConfigurationProperties(prefix = "jwt.security")
public class JwtSecurityProperties {
    
    private boolean enabled = true;
    private boolean openService = false;
    private String[] publicPaths;
    private Map<String, String[]> protectedRoutes;
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isOpenService() {
        return openService;
    }
    
    public void setOpenService(boolean openService) {
        this.openService = openService;
    }
    
    public String[] getPublicPaths() {
        return publicPaths;
    }
    
    public void setPublicPaths(String[] publicPaths) {
        this.publicPaths = publicPaths;
    }
    
    public Map<String, String[]> getProtectedRoutes() {
        return protectedRoutes;
    }
    
    public void setProtectedRoutes(Map<String, String[]> protectedRoutes) {
        this.protectedRoutes = protectedRoutes;
    }
}