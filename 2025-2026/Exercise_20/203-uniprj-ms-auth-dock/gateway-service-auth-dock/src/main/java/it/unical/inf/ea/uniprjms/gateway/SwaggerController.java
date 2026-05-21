package it.unical.inf.ea.uniprjms.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class SwaggerController {

    @GetMapping("/swagger-ui.html")
    public Mono<ResponseEntity<Void>> redirectToSwagger() {
        return Mono.just(ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/webjars/swagger-ui/5.20.1/index.html?configUrl=/swagger-config"))
                .build());
    }

    @GetMapping(value = "/swagger-config", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> swaggerConfig() {
        return Mono.just(Map.of(
                "urls", List.of(
                        Map.of("url", "/student-service/v3/api-docs", "name", "Student Service"),
                        Map.of("url", "/teacher-service/v3/api-docs", "name", "Teacher Service"),
                        Map.of("url", "/course-service/v3/api-docs", "name", "Course Service"),
                        Map.of("url", "/auth-service/v3/api-docs", "name", "Auth Service")
                )
        ));
    }
}
