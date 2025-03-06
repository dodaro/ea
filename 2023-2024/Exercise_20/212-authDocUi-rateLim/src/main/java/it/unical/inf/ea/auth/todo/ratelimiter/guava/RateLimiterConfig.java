package it.unical.inf.ea.auth.todo.ratelimiter.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    @Bean
    public RateLimiter rateLimiter() {
        // Limita a 5 richieste al minuto (5/60 richieste al secondo)
        return RateLimiter.create(5.0 / 60.0);
    }
}