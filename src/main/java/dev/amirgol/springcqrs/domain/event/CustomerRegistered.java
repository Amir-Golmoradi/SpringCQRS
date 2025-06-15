package dev.amirgol.springcqrs.domain.event;

import dev.amirgol.springcqrs.domain.vo.CustomerId;

import java.time.Instant;

public final class CustomerRegistered implements DomainEvent {
    private final CustomerId aggregateId;
    private final Instant occurredOn;

    public CustomerRegistered(CustomerId id, Instant occurredOn) {
        this.aggregateId = id;
        this.occurredOn = occurredOn;
    }

    @Override
    public CustomerId getAggregateId() {
        return aggregateId;
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }
}