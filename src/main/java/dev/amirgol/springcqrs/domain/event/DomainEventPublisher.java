package dev.amirgol.springcqrs.domain.event;

import java.util.List;

public interface DomainEventPublisher {
    void publish(List<DomainEvent> events);
}