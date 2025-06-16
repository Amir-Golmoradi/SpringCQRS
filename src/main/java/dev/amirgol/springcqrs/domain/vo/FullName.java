package dev.amirgol.springcqrs.domain.vo;

import dev.amirgol.springcqrs.domain.exception.InvalidNameException;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public record FullName(@NotNull String value) {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 100;
    // Allows letters, spaces, hyphens, and apostrophes.
    private static final Pattern FULLNAME_PATTERN =
            Pattern.compile("^[A-Za-z'-]+(?: [A-Za-z'-]+)*$");

    public FullName {
        Objects.requireNonNull(value, "Full name cannot be null");
        if (!FULLNAME_PATTERN.matcher(value.trim()).matches()) {
            throw new InvalidNameException("Full name does not meet required format. Ensure it contains only letters, spaces, and follows standard naming conventions. Provided: '" + value + "'");
        }
        if (value.length() > MAX_LENGTH) {
            throw new InvalidNameException(
                    "Full name exceeds maximum allowed length of " + MAX_LENGTH + " characters. Provided length: " + value.length() + ". Value: '" + value + "'");
        }

        if (value.length() < MIN_LENGTH) {
            throw new InvalidNameException(
                    "Full name is shorter than the minimum required length of " + MIN_LENGTH + " characters. Provided length: " + value.length() + ". Value: '" + value + "'");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}