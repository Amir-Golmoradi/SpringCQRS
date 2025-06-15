package dev.amirgol.springcqrs.domain.event;

import dev.amirgol.springcqrs.domain.vo.CustomerId;

import java.time.Instant;

public final class CustomerLoggedIn implements DomainEvent {
    private final CustomerId aggregateId;
    private final Instant occurredOn;

    public CustomerLoggedIn(CustomerId aggregateId, Instant occurredOn) {
        this.aggregateId = aggregateId;
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
