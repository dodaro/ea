package it.unical.inf.ea.uniprj.config.filter;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Order(1)
@RequiredArgsConstructor
public class AuthDummyFilter extends OncePerRequestFilter {

  private final AuditorAware<Long> currentUser;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
    try {
      if (request.getHeader(HttpHeaders.AUTHORIZATION) != null && currentUser.getCurrentAuditor().isPresent()) {
        long auth = Long.valueOf(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (currentUser.getCurrentAuditor().get() == auth) {
          filterChain.doFilter(request, response);




        } else {
          throw new AuthException("Wrong credential [" + auth + "]");
        }
      } else {
        throw new AuthException("Authorization Header not found");
      }
    } catch (Exception ex) {
      response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }
  }
}
