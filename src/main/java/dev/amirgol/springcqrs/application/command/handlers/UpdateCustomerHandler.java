package dev.amirgol.springcqrs.application.command.handlers;

import dev.amirgol.springcqrs.application.command.UpdateCustomerCommand;
import dev.amirgol.springcqrs.core.command.CommandHandler;
import dev.amirgol.springcqrs.domain.event.DomainEventPublisher;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import dev.amirgol.springcqrs.domain.value_object.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCustomerHandler implements CommandHandler<UpdateCustomerCommand, CustomerId> {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional
    public void handle(UpdateCustomerCommand command) {
        var findingCustomer = customerRepository.findByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Customer with Email " + command.email().value() + " not found."));

        // 2. Call the behavior method on the loaded aggregate instance
        // Pass the new Value Objects from the command
        // IMPORTANT: Review the password handling strategy here.
        // If updateProfile shouldn't change password, pass the existing one.
        // If it *can* change password, ensure `command.newPasswordHash()` is a properly hashed value.
        findingCustomer.updateProfile(
                command.email(),
                command.fullName(),
                new Password(passwordEncoder.encode(command.password().value()))
        );

        // 3. Persist the modified aggregate
        customerRepository.save(findingCustomer);

        // 4. Publish Domain Events (CRITICAL)
        // This is often done by a Spring @TransactionalEventListener
        // hooked into your PersistenceContext, which pulls events after commit.
        // If not using TransactionalEventListener, you'd explicitly publish here:

        // For your learning: The most robust way to publish events is usually
        // through a Transactional Outbox Pattern to guarantee atomicity
        // between DB commit and event publication.
        // However, for basic learning, Spring's @TransactionalEventListener is a good start.
        domainEventPublisher.publish(findingCustomer.pullDomainEvent());
    }
}

