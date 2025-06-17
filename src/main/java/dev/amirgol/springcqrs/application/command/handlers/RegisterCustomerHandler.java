package dev.amirgol.springcqrs.application.command.handlers;

import dev.amirgol.springcqrs.application.command.RegisterCustomerCommand;
import dev.amirgol.springcqrs.core.command.CommandHandler;
import dev.amirgol.springcqrs.domain.event.DomainEventPublisher;
import dev.amirgol.springcqrs.domain.model.Customer;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import dev.amirgol.springcqrs.domain.vo.Password;
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
    public CustomerId handle(RegisterCustomerCommand cmd) {
        var customer = Customer.register(
                new Email(cmd.email().value()),
                new FullName(cmd.fullName().value()),
                new Password(passwordEncoder.encode(cmd.password().value()))
        );

        customerRepository.save(customer);
        domainEventPublisher.publish(customer.pullDomainEvent());
        return customer.getId();
    }
}
