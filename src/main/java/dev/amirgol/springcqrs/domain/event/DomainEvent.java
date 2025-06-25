package dev.amirgol.springcqrs.domain.event;

import dev.amirgol.springcqrs.domain.value_object.CustomerId;

import java.time.Instant;

public interface DomainEvent {
    CustomerId aggregateId();
    Instant occurredOn();
}
