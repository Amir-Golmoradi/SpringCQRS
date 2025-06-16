package dev.amirgol.springcqrs.domain.vo;

import dev.amirgol.springcqrs.domain.exception.InvalidEmailException;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;


public record Email(@NotNull String value) {
    private static final int MAX_LENGTH = 255;
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public Email {
        Objects.requireNonNull(value, "Email cannot be null");
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidEmailException("Invalid email: " + value);
        }
        if (value.length() > MAX_LENGTH) {
            throw new InvalidEmailException("Invalid email length: " + value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
