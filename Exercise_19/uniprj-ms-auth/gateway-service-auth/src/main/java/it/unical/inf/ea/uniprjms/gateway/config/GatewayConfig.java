package it.unical.inf.ea.uniprjms.gateway.config;

import it.unical.inf.ea.uniprjms.gateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig
{
	private final JwtAuthenticationFilter jwtAuthFilter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder)
	{
		/*
		Questa sezione sovrascrive l'indirizzamento delle rotte sul file gateway.yml

		Se si vuole utilizzare l'indirizzamento delle rotte su file yml, creare un GatewayFilterFactory
		e utilizzarlo nella sezione
		filters:
        - RewritePath=/student-api/(?<path>.*), /$\{path}
        - [HERE]
		 */
		return builder
				.routes()
					.route("course-service", r -> r.path("/course-api/**")
								//.and().method("GET")
							.filters(f -> f.filter(jwtAuthFilter).rewritePath("/course-api/(?<path>.*)", "/$\\{path}"))
							.uri("lb://course-service"))
					.route("student-service", r -> r.path("/student-api/**")
								//.and().method("GET,POST,DELETE")
							.filters(f -> f.filter(jwtAuthFilter).rewritePath("/student-api/(?<path>.*)", "/$\\{path}"))
							.uri("lb://student-service"))
					.route("teacher-service", r -> r.path("/teacher-api/**")
								//.and().method("GET")
							.filters(f -> f.filter(jwtAuthFilter).rewritePath("/teacher-api/(?<path>.*)", "/$\\{path}"))
							.uri("lb://teacher-service"))
				   .route("auth-service", r -> r.path("/auth-api/**")
						   .filters(f -> f.filter(jwtAuthFilter).rewritePath("/auth-api/(?<path>.*)", "/$\\{path}"))
						   .uri("lb://auth-service"))

				.build();
	}
}
