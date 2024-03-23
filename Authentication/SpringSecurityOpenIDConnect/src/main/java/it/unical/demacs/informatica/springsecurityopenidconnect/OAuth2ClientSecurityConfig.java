package it.unical.demacs.informatica.springsecurityopenidconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class OAuth2ClientSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                logout(
                        lgt -> {
                            lgt.permitAll();
                            lgt.invalidateHttpSession(true);
                            lgt.clearAuthentication(true);
                            lgt.deleteCookies("JSESSIONID");
                        }
                ).authorizeHttpRequests(
                        auth -> auth.anyRequest().authenticated()
                ).oauth2Login(Customizer.withDefaults())
                .oauth2Client(Customizer.withDefaults()).build();
    }
}
