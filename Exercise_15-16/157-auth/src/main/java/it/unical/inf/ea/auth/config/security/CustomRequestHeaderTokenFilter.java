/*
 * Dealing with both: Bearer and Basic Authorization Headers
 * for authorization and/or authentication respectively
 */

package it.unical.inf.ea.auth.config.security;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomRequestHeaderTokenFilter extends OncePerRequestFilter {

  @Autowired
  LoggedUserDetailsService userService;

  private final Log logger = LogFactory.getLog(getClass());
  private AuthenticationManager authManager;

  public CustomRequestHeaderTokenFilter(AuthenticationManager authenticationManager) {
    Assert.notNull(authenticationManager, "AuthenticationManager cannot be null");
    this.authManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
      throws jakarta.servlet.ServletException, IOException {

    String uname = "";
    String upassw = "";
    UsernamePasswordAuthenticationToken authentication;
    String uri = request.getRequestURI();
    logger.info("Request URI: " + uri);

    try {
      String headerToken = "";
      headerToken = request.getHeader(SecurityConstants.AUTH_HEADER);
      logger.info("Authorization Header value: " + headerToken);

      if (headerToken == null || (!headerToken.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) && !headerToken.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX))) {
        this.logger.trace("No Authorization header found!");
        filterChain.doFilter(request, response);
        return;
      }

      if (headerToken.startsWith(SecurityConstants.BASIC_TOKEN_PREFIX) && uri.endsWith(SecurityConstants.LOGIN_URI_ENDING)) {
        headerToken = StringUtils.delete(headerToken, SecurityConstants.BASIC_TOKEN_PREFIX).trim();
        uname = TokenUtils.decodedBase64(headerToken)[0];
        upassw = TokenUtils.decodedBase64(headerToken)[1];
        this.logger.trace(LogMessage.format("Credentials username '%s' and password '&s' have been found in Basic Authorization header", uname, upassw));

        authentication = new UsernamePasswordAuthenticationToken(uname, upassw);
        Authentication authResult = this.authManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        logger.info("(Authenticated) Authentication: " + authResult.toString());

      } else if (headerToken.startsWith(SecurityConstants.BEARER_TOKEN_PREFIX) && !uri.endsWith(SecurityConstants.LOGIN_URI_ENDING)) {
        headerToken = StringUtils.delete(headerToken, SecurityConstants.BEARER_TOKEN_PREFIX).trim();
        if (TokenUtils.isJWTTokenValid(headerToken)) {
          uname = TokenUtils.getUsernameFromJWTUserToken(headerToken);
          this.logger.trace(LogMessage.format("username '%s' extracted from Bearer Authorization header", uname));

          UserDetails userDetails = userService.loadUserByUsername(uname);
          if (userDetails != null) {
            authentication = new UsernamePasswordAuthenticationToken(uname, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.info("(Authorized) Authentication: " + authentication.toString());

          }
        }
      }

    } catch (AuthenticationException ex) {
      this.logger.info("Failed to process authentication request: " + ex.getMessage());
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    filterChain.doFilter(request, response);

  }

}






