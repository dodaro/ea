package it.unical.inf.ea.uniprjms.shared.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/*
 * Intercetta ogni richiesta HTTP, estrae e valida il token JWT dall'header Authorization e autentica l'utente.
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        
        try {
            // Estrai le credenziali dal token
            JwtUserClaims userClaims = jwtService.extractUserClaims(jwt);
            
            if (userClaims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Crea UserDetails dalla rappresentazione delle authorities nel JWT
                UserDetails userDetails = JwtUserDetails.fromUserClaims(userClaims);
                
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception ignored) {
            // In caso di errore, continua senza autenticazione
        }

        filterChain.doFilter(request, response);
    }
}