package it.unical.demacs.informatica.springsession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/api/v1/register").permitAll();
                            auth.anyRequest().authenticated();
                        }
                ).
                logout(lgt -> {
                    lgt.invalidateHttpSession(true);
                    lgt.logoutSuccessUrl("/login");
                }).
                formLogin(Customizer.withDefaults()).
                build();
    }
}
