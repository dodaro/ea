package it.unical.inf.ea.auth.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unical.inf.ea.auth.config.security.JwtUtil;
import it.unical.inf.ea.auth.config.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        logger.info("Request URI: " + uri);

        String token = null;

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        logger.info("Authorization Header value: " + authorizationHeader);

        if (authorizationHeader == null || (!authorizationHeader.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) && !authorizationHeader.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX))) {
            this.logger.trace("No Authorization header found!");
            filterChain.doFilter(request, response);
            return;
        }

        if ((authorizationHeader.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) && uri.endsWith(SecurityConstants.LOGIN_URI_ENDING))
            || uri.endsWith(SecurityConstants.REFRESH_TOKEN_URI_ENDING)) {
            filterChain.doFilter(request, response);

        } else {

            if(authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX) && !uri.endsWith(SecurityConstants.LOGIN_URI_ENDING)) {
                try {
                    token = authorizationHeader.substring("Bearer ".length());
                    UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }
                catch (Exception e) {
                    log.error(String.format("Error auth token: %s", token), e);
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("errorMessage", e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
