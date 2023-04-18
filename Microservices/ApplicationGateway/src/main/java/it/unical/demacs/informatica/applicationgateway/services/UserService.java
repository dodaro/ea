package it.unical.demacs.informatica.applicationgateway.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class UserService {

    private final static String USER_HANDLER_SERVICE = "user-handler-service";

    private final static String USER_SERVICE = "userService";

    private final WebClient webClient;

    private final ServiceUtil serviceUtil;


    public UserService(WebClient webClient, ServiceUtil serviceUtil) {
        this.webClient = webClient;
        this.serviceUtil = serviceUtil;
    }

    @CircuitBreaker(name=USER_SERVICE)
    @Retry(name=USER_SERVICE)
    public Mono<String> register(String username, String email, boolean consent) {
        return webClient
                .get()
                .uri(serviceUtil.createGETUri(USER_HANDLER_SERVICE, "/api/v1/register", Map.of("username", username, "email", email, "consent", consent)))
                .retrieve()
                .bodyToMono(String.class);
    }

    @CircuitBreaker(name=USER_SERVICE)
    @Retry(name=USER_SERVICE)
    public Mono<String> findUserDetails(String username) {
        return webClient
                .get()
                .uri(serviceUtil.getServer(USER_HANDLER_SERVICE) + "/api/v1/users/{username}", username)
                .retrieve()
                .bodyToMono(String.class);
    }

}
