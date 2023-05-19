package it.unical.inf.ea.uniprjms.gateway.filter;

import it.unical.inf.ea.uniprjms.gateway.config.JwtUtil;
import it.unical.inf.ea.uniprjms.gateway.config.LoggedUserToken;
import it.unical.inf.ea.uniprjms.gateway.config.RouterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.unical.inf.ea.uniprjms.gateway.config.JwtUtil.AUTHORIZATION;
import static it.unical.inf.ea.uniprjms.gateway.config.JwtUtil.BEARER_TOKEN_PREFIX;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
@Slf4j
@RequiredArgsConstructor
@RefreshScope
public class JwtAuthenticationFilter implements GatewayFilter 
{
	@Autowired
	private RouterValidator routerValidator;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) 
	{

		ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

		if (routerValidator.isSecured.test(request)) {
			if (!request.getHeaders().containsKey(AUTHORIZATION)) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.UNAUTHORIZED);

				return response.setComplete();
			}

			final String token = request.getHeaders().getOrEmpty(AUTHORIZATION).get(0).replace(BEARER_TOKEN_PREFIX, "");

			try {
				//			jwtUtil.validateToken(token);
				LoggedUserToken authenticationToken = JwtUtil.parseToken(token);
				List<String> authorities = authenticationToken.getAuths();
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				log.info(authenticationToken.getUsername());

				if (!isAuthorized(exchange, authorities)) {
					ServerHttpResponse response = exchange.getResponse();
					response.setStatusCode(HttpStatus.FORBIDDEN);

					return response.setComplete();
				}
			} catch (Exception e) {
				ServerHttpResponse response = exchange.getResponse();
				log.error(String.format("Error auth token: %s", token), e);
				response.setStatusCode(FORBIDDEN);
				Map<String, String> error = new HashMap<>();
				error.put("errorMessage", e.getMessage());
			}

		}
		return chain.filter(exchange);
	}


	private boolean isAuthorized(ServerWebExchange exchange, List<String> userRoles) {

		// Verifica i ruoli in base alla rotta richiesta
		String path = exchange.getRequest().getPath().value();
		if (path.startsWith("/student-api")) {
			return userRoles.contains("ROLE_ADMIN");
		} else if (path.startsWith("/teacher-api") || path.startsWith("/course-api")) {
			return userRoles.contains("ROLE_BASIC");
		}

		// Restituisce false se la rotta richiesta non corrisponde a nessun caso
		return false;
	}

}
