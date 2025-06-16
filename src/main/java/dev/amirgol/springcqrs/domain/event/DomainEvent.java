package dev.amirgol.springcqrs.domain.event;

import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.FullName;

import java.time.Instant;

public interface DomainEvent {
    CustomerId aggregateId();
    Instant occurredOn();
}
