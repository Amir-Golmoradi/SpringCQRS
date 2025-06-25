package dev.amirgol.springcqrs.domain.event.child;

import dev.amirgol.springcqrs.domain.event.DomainEvent;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;

import java.time.Instant;

public record CustomerProfileUpdated(
        CustomerId aggregateId,
        String email,
        String name,
        String password,
        Instant occurredOn
) implements DomainEvent {
}
