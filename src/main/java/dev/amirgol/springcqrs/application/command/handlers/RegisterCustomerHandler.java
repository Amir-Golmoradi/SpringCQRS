package dev.amirgol.springcqrs.application.command.handlers;

import dev.amirgol.springcqrs.application.command.RegisterCustomerCommand;
import dev.amirgol.springcqrs.core.command.CommandHandler;
import dev.amirgol.springcqrs.domain.event.DomainEventPublisher;
import dev.amirgol.springcqrs.domain.model.Customer;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import dev.amirgol.springcqrs.domain.value_object.Email;
import dev.amirgol.springcqrs.domain.value_object.FullName;
import dev.amirgol.springcqrs.domain.value_object.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterCustomerHandler implements CommandHandler<RegisterCustomerCommand, CustomerId> {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional
    public void handle(RegisterCustomerCommand command) {
        var customer = Customer.register(
                new Email(command.email().value()),
                new FullName(command.fullName().value()),
                new Password(passwordEncoder.encode(command.password().value()))
        );

        customerRepository.save(customer);
        domainEventPublisher.publish(customer.pullDomainEvent());
    }
}
