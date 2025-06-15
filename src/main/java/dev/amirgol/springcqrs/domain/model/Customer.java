package dev.amirgol.springcqrs.domain.model;

import dev.amirgol.springcqrs.domain.event.CustomerLoggedIn;
import dev.amirgol.springcqrs.domain.event.DomainEvent;
import dev.amirgol.springcqrs.domain.exception.AggregateException;
import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import dev.amirgol.springcqrs.domain.vo.Password;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Customer {
    private final List<DomainEvent> domainEvent = new ArrayList<>();
    private CustomerId id;
    private Email email;
    private FullName fullName;
    private Password password;
    private Instant registrationDate;
    private Instant lastLoginDate;

    // ========== DOMAIN EVENTS ==========

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
        setEmail(email);
        setFullName(fullName);
        setPassword(password);
        raiseEvent(new CustomerLoggedIn(id, lastLoginDate));
    }

    private void raiseEvent(DomainEvent event) {
        domainEvent.add(event);
    }

    private List<DomainEvent> pullDomainEvent() {
        var events = new ArrayList<>(domainEvent);
        domainEvent.clear();
        return Collections.unmodifiableList(events);
    }

    public void login() {
        this.lastLoginDate = Instant.now();
        raiseEvent(new CustomerLoggedIn(id, lastLoginDate));
    }

    public CustomerId getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    // ========== SETTERS ==========
    private void setEmail(Email email) {
        if (email == null) {
            throw new AggregateException("Email cannot be null");
        }
        this.email = email;
    }

    public FullName getFullName() {
        return fullName;
    }

    private void setFullName(FullName fullName) {
        if (fullName == null) {
            throw new AggregateException("Full name cannot be null");
        }
        this.fullName = fullName;
    }

    public Password getPassword() {
        return password;
    }

    private void setPassword(Password password) {
        if (password == null) {
            throw new AggregateException("Password cannot be null");
        }
    }

    public Instant getLastLogin() {
        return lastLoginDate;
    }
}



