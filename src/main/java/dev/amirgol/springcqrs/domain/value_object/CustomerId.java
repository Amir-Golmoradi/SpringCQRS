package dev.amirgol.springcqrs.domain.value_object;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(@NotNull UUID value) {
    public CustomerId {
        Objects.requireNonNull(value, "Customer value must not be null");
    }

    public static CustomerId generate() {
        return new CustomerId(UUID.randomUUID());
    }
}
