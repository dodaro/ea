package it.unical.inf.ea.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String BASIC = "basic";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//            .requestMatchers(HttpMethod.GET, "/products/welcome").permitAll()
//            .requestMatchers(HttpMethod.GET, "/products/all").hasRole(ADMIN)
//            .requestMatchers(HttpMethod.GET, "/products/random").hasAnyRole(ADMIN, BASIC)
//            .requestMatchers(HttpMethod.GET, "/products/{id}").hasRole(BASIC)
//            .anyRequest().authenticated();
//        http.oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthConverter);
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.authorizeHttpRequests()
            .anyRequest().fullyAuthenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .oauth2ResourceServer().jwt()
            .and()
            .and()
//            .oauth2Login()
            .cors().and().csrf().disable();



        return http.build();
    }

}