package dev.amirgol.springcqrs.domain.vo;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public record FullName(@NotNull String value) {
    private static final Pattern FULLNAME_PATTERN =
            Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)*+$");

    public FullName {
        Objects.requireNonNull(value, "Full name cannot be null");
        if (!FULLNAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid full name: " + value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}