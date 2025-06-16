package dev.amirgol.springcqrs.domain.event;

import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import dev.amirgol.springcqrs.domain.vo.Password;

import java.time.Instant;

public record CustomerLoggedIn(CustomerId aggregateId, Instant occurredOn) implements DomainEvent {
}

