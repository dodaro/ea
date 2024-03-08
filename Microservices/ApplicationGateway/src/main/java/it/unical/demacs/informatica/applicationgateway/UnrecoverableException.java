package it.unical.demacs.informatica.applicationgateway;

public class UnrecoverableException extends RuntimeException {
    public UnrecoverableException() {
        super();
    }

    public UnrecoverableException(String errorBody) {
        super(errorBody);
    }
}
