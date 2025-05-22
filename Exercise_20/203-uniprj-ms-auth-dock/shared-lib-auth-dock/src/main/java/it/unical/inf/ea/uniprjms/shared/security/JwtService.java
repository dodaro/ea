package it.unical.inf.ea.uniprjms.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Gestisce la creazione, validazione ed estrazione di informazioni dai token JWT.
 */
public class JwtService {
    
    @Setter
    private String jwtSecret;
    
    @Setter
    private long jwtExpiration = 3600000; // Default 1 ora
    
    @Setter
    private long refreshExpiration = 86400000; // Default 24 ore

    /**
     * Estrae le informazioni dell'utente dal token JWT
     */
    public JwtUserClaims extractUserClaims(String token) {
        Claims claims = extractAllClaims(token);
        String username = claims.getSubject();
        
        @SuppressWarnings("unchecked")
        List<String> authorities = claims.get("roles", List.class);
        
        return new JwtUserClaims(username, authorities);
    }

    /**
     * Estrae lo username dal token JWT
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Estrae una specifica informazione dalle claims del token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Genera un token JWT per un utente
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(extractClaims(userDetails), userDetails);
    }

    /**
     * Genera un token JWT con claims personalizzate
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Genera un refresh token
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(extractClaims(userDetails), userDetails, refreshExpiration);
    }

    /**
     * Costruisce il token JWT
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Estrae le claims dell'utente dai dettagli utente
     */
    private Map<String, Object> extractClaims(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
                
        return Map.of("roles", roles);
    }

    /**
     * Verifica se il token è valido per l'utente specificato
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Verifica se il token è valido (non scaduto e firmato correttamente)
     */
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se il token è scaduto
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Estrae la data di scadenza dal token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Estrae tutte le claims dal token JWT
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Ottiene la chiave per la firma JWT
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}