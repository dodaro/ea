package it.unical.inf.ea.lombok.ex2;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class LoginResult {

    private final @NonNull Instant loginTs;

    private @NonNull @Setter String authToken;

    private final @NonNull Duration tokenValidity;
    
    private final @NonNull URL tokenRefreshUrl;

    public static void main(String[] args) {
        LoginResult lr = new LoginResult(null,null,null,null);
        lr.authToken("test");
        Instant instant = lr.loginTs();
    }
}