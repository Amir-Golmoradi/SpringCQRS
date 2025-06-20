package dev.amirgol.springcqrs.domain.exception;

public class AggregateException extends RuntimeException {
    public AggregateException(String message) {
        super(message);
    }

    public AggregateException(String message, Throwable cause) {
        super(message, cause);
    }
}
