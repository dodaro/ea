package it.unical.inf.ea.auth.todo.ratelimiter;

public class ManyRequestException extends RuntimeException {

    public ManyRequestException(String message) {
        super(message);
    }
}
