package dev.amirgol.springcqrs.domain.event.child;

import dev.amirgol.springcqrs.domain.event.DomainEvent;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;

import java.time.Instant;

public record CustomerLoggedIn(CustomerId aggregateId, Instant occurredOn) implements DomainEvent {
}

