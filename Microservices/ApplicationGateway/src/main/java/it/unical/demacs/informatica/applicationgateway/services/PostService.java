package it.unical.demacs.informatica.applicationgateway.services;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    private final static String POST_HANDLER_SERVICE = "post-handler-service";
    private final static String POST_SERVICE = "postService";
    private final WebClient webClient;
    private final ServiceUtil serviceUtil;

    public PostService(WebClient webClient, ServiceUtil serviceUtil) {
        this.webClient = webClient;
        this.serviceUtil = serviceUtil;
    }

    public Mono<String> emptyList(Exception e) {
        return Mono.just("[]");
    }

    public Mono<String> fallbackCircuitBreaker(CallNotPermittedException e) {
        System.out.println("CIRCUIT BREAKER");
        return Mono.just("[]");
    }

    @CircuitBreaker(name=POST_SERVICE, fallbackMethod = "fallbackCircuitBreaker")
    @Retry(name=POST_SERVICE, fallbackMethod = "emptyList")
    public Mono<String> findAllPosts(String username) {
        return webClient
                .get()
                .uri(serviceUtil.getServer(POST_HANDLER_SERVICE) + "/api/v1/posts/{username}", username)
                .retrieve()
                .bodyToMono(String.class);
    }

    @CircuitBreaker(name=POST_SERVICE)
    @Retry(name=POST_SERVICE)
    public Mono<String> createPost(String username, String text) {
        return webClient
                .post()
                .uri(serviceUtil.getServer(POST_HANDLER_SERVICE) + "/api/v1/posts/{username}", username)
                .body(BodyInserters.fromValue(text))
                .retrieve()
                .bodyToMono(String.class);
    }

}
