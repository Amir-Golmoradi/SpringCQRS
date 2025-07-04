package dev.amirgol.springcqrs.domain.repository;

import dev.amirgol.springcqrs.domain.model.Customer;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import dev.amirgol.springcqrs.domain.value_object.Email;
import dev.amirgol.springcqrs.domain.value_object.FullName;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(CustomerId id);

    Optional<Customer> findByName(FullName fullName);

    Optional<Customer> findByEmail(Email email);

    void save(Customer customer);

    void update(Customer customer);

    void deleteByEmail(Email email);
}
