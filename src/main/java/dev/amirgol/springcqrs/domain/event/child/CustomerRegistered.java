package dev.amirgol.springcqrs.domain.event.child;

import dev.amirgol.springcqrs.domain.event.DomainEvent;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;

import java.time.Instant;

public final class CustomerRegistered implements DomainEvent {
    private final CustomerId aggregateId;
    private final Instant occurredOn;

    public CustomerRegistered(CustomerId id, Instant occurredOn) {
        this.aggregateId = id;
        this.occurredOn = occurredOn;
    }

    @Override
    public CustomerId aggregateId() {
        return aggregateId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}