package it.unical.inf.ea.auth.todo.ratelimiter.bucket4j;

public class ManyRequestException extends RuntimeException {

    public ManyRequestException(String message) {
        super(message);
    }
}
