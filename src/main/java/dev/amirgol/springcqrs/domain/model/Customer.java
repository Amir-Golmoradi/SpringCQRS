package dev.amirgol.springcqrs.domain.model;

import dev.amirgol.springcqrs.domain.event.CustomerLoggedIn;
import dev.amirgol.springcqrs.domain.event.CustomerProfileUpdated;
import dev.amirgol.springcqrs.domain.event.DomainEvent;
import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import dev.amirgol.springcqrs.domain.vo.Password;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Customer {
    private final List<DomainEvent> domainEvent = new ArrayList<>();

    @Getter
    private final CustomerId id;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Email email;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private FullName fullName;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Password password;

    private Instant registrationDate;

    private Instant lastLoginDate;

    public Customer(
            CustomerId id, Email email, FullName fullName,
            Password password, Instant registrationDate
    ) {
        this.id = Objects.requireNonNull(id);
        setEmail(email);
        setFullName(fullName);
        setPassword(password);
        this.registrationDate = Objects.requireNonNull(registrationDate);
        this.lastLoginDate = registrationDate;
        raiseEvent(new CustomerLoggedIn(id, lastLoginDate));
    }

    // 2. Factory Method for registration.
    public static Customer register(Email email, FullName fullName, Password password) {
        return new Customer(CustomerId.generate(), email, fullName, password, Instant.now());
    }

    public void updateProfile(Email email, FullName fullName, Password password) {

        if (!Objects.equals(this.email, email)) {
            setEmail(email);
        }
        if (!Objects.equals(this.fullName, fullName)) {
            setFullName(fullName);
        }
        if (!Objects.equals(this.password, password)) {
            setPassword(password);
        }
        raiseEvent(
                new CustomerProfileUpdated(
                        id,
                        this.email.value(),
                        this.fullName.value(),
                        this.password.value(),
                        Instant.now()
                )
        );
    }

    private void raiseEvent(DomainEvent event) {
        domainEvent.add(event);
    }

    public List<DomainEvent> pullDomainEvent() {
        var events = new ArrayList<>(domainEvent);
        domainEvent.clear();
        return Collections.unmodifiableList(events);
    }

    public void login() {
        this.lastLoginDate = Instant.now();
        raiseEvent(new CustomerLoggedIn(id, lastLoginDate));
    }

    public Instant getLastLogin() {
        return lastLoginDate;
    }
}
