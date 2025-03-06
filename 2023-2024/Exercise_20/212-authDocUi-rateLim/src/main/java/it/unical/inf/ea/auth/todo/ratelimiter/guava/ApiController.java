package it.unical.inf.ea.auth.todo.ratelimiter.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RateLimiter rateLimiter;

    @Autowired
    public ApiController(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @GetMapping("/limited-endpoint")
    public ResponseEntity<String> limitedEndpoint() {
        if (rateLimiter.tryAcquire()) {
            // Elabora la richiesta normalmente
            return ResponseEntity.ok("Request processed successfully.");
        } else {
            // Rifiuta la richiesta se il limite è stato raggiunto
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests - try again later.");
        }
    }
}
