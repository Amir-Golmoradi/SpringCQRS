package dev.amirgol.springcqrs.domain.event;

import dev.amirgol.springcqrs.domain.vo.CustomerId;

import java.time.Instant;

public record CustomerProfileUpdated(
        CustomerId aggregateId,
        String email,
        String name,
        String password,
        Instant occurredOn
) implements DomainEvent {
}
