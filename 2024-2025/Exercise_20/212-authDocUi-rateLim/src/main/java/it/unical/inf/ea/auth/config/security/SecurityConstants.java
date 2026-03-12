package it.unical.inf.ea.auth.config.security;

public class SecurityConstants {
    
    public static final String JWT_SECRET = "t3pCSx2wx1ExbQ5z43XXB8my/KR24aon4EH/niU9iZi1I3S69rk1QhlMFFsTrZIY";
    //public static final String JWT_SECRET = "PanosSecretKeyForJWTToGen";
    //public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final long EXPIRATION_TIME = 36_000_000; // 10 hours
    //public static final long EXPIRATION_TIME = 3_600_000;// 1 hour
    //public static final long EXPIRATION_TIME = 600_000; // 10 minutes
//    public static final long EXPIRATION_TIME = 60_000; // 1 minutes
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String BASIC_TOKEN_PREFIX =  "Basic ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String LOGIN_URI_ENDING = "/signin";
    public static final String REALM_HEADER = "WWW-Authenticate";

}

