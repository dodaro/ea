package it.unical.inf.ea.auth.config.security;

public class SecurityConstants {

    public static final String JWT_SECRET = "t3pCSx2wx1ExbQ5z43XXB8my/KR24aon4EH/niU9iZi1I3S69rk1QhlMFFsTrZIY";
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

}

