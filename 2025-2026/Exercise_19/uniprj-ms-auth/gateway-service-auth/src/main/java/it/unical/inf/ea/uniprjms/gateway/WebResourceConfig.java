package it.unical.inf.ea.uniprjms.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebResourceConfig {

    @Bean
    public RouterFunction<ServerResponse> webjarsRouter() {
        return RouterFunctions.resources("/webjars/**",
                new ClassPathResource("/META-INF/resources/webjars/"));
    }
}
