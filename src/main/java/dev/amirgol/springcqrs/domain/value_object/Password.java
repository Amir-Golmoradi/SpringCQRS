package dev.amirgol.springcqrs.domain.value_object;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public record Password(@NotNull String value) {
    // At least 8 chars, one uppercase, one lowercase, one number, one special char
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$");

    public Password {
        Objects.requireNonNull(value, "Password cannot be null");
        if (!PASSWORD_PATTERN.matcher(value).matches()) {
            throw new ValidationException("Your password is too weak");
        }
    }

    @Override
    public String toString() {
        return "********"; // Never print the real password!
    }
}