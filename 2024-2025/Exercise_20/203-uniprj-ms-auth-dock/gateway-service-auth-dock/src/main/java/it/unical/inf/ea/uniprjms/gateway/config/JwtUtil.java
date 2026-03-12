package it.unical.inf.ea.uniprjms.gateway.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public abstract class JwtUtil {

    //public static final long EXPIRATION_TIME = 864_000; // 10 days
    //public static final long EXPIRATION_TIME = 36_000; // 10 hours
    //public static final long EXPIRATION_TIME = 3_600;// 1 hour
    //public static final long EXPIRATION_TIME = 600; // 10 minutes
    public static final long EXPIRATION_TIME = 60; // 1 minutes
    public static final long EXPIRATION_REFRESH_TOKEN_TIME = 36_000; // 1 minutes
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String BASIC_TOKEN_PREFIX =  "Basic ";
    public static final String LOGIN_URI_ENDING = "/login";
    public static final String REFRESH_TOKEN_URI_ENDING = "/refreshToken";

    public static final String JWT_SECRET = "t3pCSx2wx1ExbQ5z43XXB8my/KR24aon4EH/niU9iZi1I3S69rk1QhlMFFsTrZIY";
    private static SecretKey SECRET = new SecretKeySpec(Base64.getDecoder().decode(JWT_SECRET), "HmacSHA256");
    public static final String AUTHORIZATION = "Authorization";


    public static String createAccessToken(String username, String issuer, List<String> roles) {
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer(issuer)
                    .claim("roles", roles)
                    .expirationTime(Date.from(Instant.now().plusSeconds(EXPIRATION_TIME)))
                    .issueTime(new Date())
                    .build();

            Payload payload = new Payload(claims.toJSONObject());

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
                    payload);

            jwsObject.sign(new MACSigner(SECRET));
            return jwsObject.serialize();
        }
        catch (JOSEException e) {
            throw new RuntimeException("Error to create JWT", e);
        }
    }

    public static String createRefreshToken(String username) {
        //like createAccessToken method, but without issuer, roles...

        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(username)
                .expirationTime(Date.from(Instant.now().plusSeconds(EXPIRATION_REFRESH_TOKEN_TIME)))
                .issueTime(new Date())
                .build();

            Payload payload = new Payload(claims.toJSONObject());

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
                payload);

            jwsObject.sign(new MACSigner(SECRET));
            return jwsObject.serialize();
        }
        catch (JOSEException e) {
            throw new RuntimeException("Error to create JWT", e);
        }
    }

    public static LoggedUserToken extractToken(HttpServletRequest request) throws ParseException, JOSEException, BadJOSEException {
        return parseToken(request.getHeader(AUTHORIZATION).replace(BEARER_TOKEN_PREFIX, ""));
    }

    public static LoggedUserToken parseToken(String token) throws ParseException, JOSEException, BadJOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        signedJWT.verify(new MACVerifier(SECRET));
        ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

        JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.HS256,
                new ImmutableSecret<>(SECRET));
        jwtProcessor.setJWSKeySelector(keySelector);
        jwtProcessor.process(signedJWT, null);
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
        String username = claims.getSubject();
        var roles = (List<String>) claims.getClaim("roles");
        return new LoggedUserToken(username, null, roles);
    }

    public static String[] decodedBase64(String token) {

        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String pairedCredentials = new String(decodedBytes);
        String[] credentials = pairedCredentials.split(":", 2);

        return credentials;
    }
}