package it.unical.demacs.informatica.springsecurityopenidconnect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class OAuth2ClientSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .logout().permitAll().invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID").and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and().oauth2Login()
                .and().oauth2Client()
                .and().build();
    }
}
