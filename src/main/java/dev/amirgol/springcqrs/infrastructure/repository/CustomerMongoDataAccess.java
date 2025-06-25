package dev.amirgol.springcqrs.infrastructure.repository;

import dev.amirgol.springcqrs.application.dto.mapper.CustomerMapper;
import dev.amirgol.springcqrs.domain.model.Customer;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import dev.amirgol.springcqrs.domain.value_object.Email;
import dev.amirgol.springcqrs.domain.value_object.FullName;
import dev.amirgol.springcqrs.domain.value_object.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerMongoDataAccess implements CustomerRepository {
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;
    private final CustomerMongoRepository repository;

    @Override
    public Optional<Customer> findById(CustomerId id) {
        if (!repository.existsById(id.value())) {
            throw new IllegalArgumentException("Customer not found");
        }
        return repository
                .findById(id.value())
                .stream()
                .map(mapper::toDomain)
                .findFirst();
    }

    @Override
    public Optional<Customer> findByName(FullName fullName) {
        if (!repository.existsByFullName(fullName.value())) {
            throw new IllegalArgumentException("Customer with name " + fullName + " does not exist");
        }
        return repository
                .findByFullName(fullName.value())
                .stream()
                .map(mapper::toDomain)
                .findFirst();
    }

    @Override
    public Optional<Customer> findByEmail(Email email) {
        if (!repository.existsByEmail(email.value())) {
            throw new IllegalArgumentException("Customer with email " + email + " does not exist");
        }
        return repository
                .findByEmail(email.value())
                .stream()
                .map(mapper::toDomain)
                .findFirst();
    }

    @Override
    public void save(Customer customer) {
        repository.save(mapper.toMongoEntity(customer));
    }

    @Override
    public void update(Customer customer) {
        var customerEmail = customer.getEmail();
        Customer customerMongoEntity = repository.findByEmail(customerEmail.value())
                .stream()
                .map(mapper::toDomain)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Customer with Email " + customerEmail.value() + " not found."));


        customerMongoEntity.updateProfile(
                new Email(customerEmail.value()),
                new FullName(customer.getFullName().value()),
                new Password(passwordEncoder.encode(customer.getPassword().value()))
        );
        repository.save(mapper.toMongoEntity(customerMongoEntity));
    }

    @Override
    public void deleteByEmail(Email email) {
        if (!repository.existsByEmail(email.value())) {
            throw new IllegalArgumentException("Customer with email " + email + " does not exist");
        }
        repository.deleteByEmail(email.value());
    }
}
