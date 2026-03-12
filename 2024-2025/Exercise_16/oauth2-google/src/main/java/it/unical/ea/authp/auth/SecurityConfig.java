package it.unical.ea.authp.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationHandler oAuth2AuthenticationHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Configurazione Security Filter Chain");

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    log.info("Configurazione delle regole di autorizzazione");
                    authorize
                            .requestMatchers("/info", "/debug/**").permitAll()
                            .requestMatchers("/").authenticated() // L'endpoint radice richiede autenticazione
                            .requestMatchers("/basic").hasAuthority("ROLE_BASIC")
                            .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                            .anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    log.info("Configurazione OAuth2 Login");
                    oauth2
                            .userInfoEndpoint(userInfo -> {
                                log.info("Impostazione del CustomOAuth2UserService");
                                userInfo.userService(customOAuth2UserService);
                            })
                            .successHandler(oAuth2AuthenticationHandler)
                            .defaultSuccessUrl("/info", true);
                })
                .logout(logout -> {
                    log.info("Configurazione Logout");
                    logout
                            .logoutSuccessUrl("/info")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                })
                //.addFilterAfter(oAuth2AuthenticationHandler.createAfterFilter(), BasicAuthenticationFilter.class)
                ;

        return http.build();
    }
}
