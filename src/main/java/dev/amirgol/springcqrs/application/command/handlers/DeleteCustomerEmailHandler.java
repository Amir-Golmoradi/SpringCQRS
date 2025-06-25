package dev.amirgol.springcqrs.application.command.handlers;

import dev.amirgol.springcqrs.application.command.DeleteCustomerEmailCommand;
import dev.amirgol.springcqrs.core.command.CommandHandler;
import dev.amirgol.springcqrs.core.exception.ValidationException;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCustomerEmailHandler implements CommandHandler<DeleteCustomerEmailCommand, CustomerId> {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void handle(DeleteCustomerEmailCommand command) {
        var customerEmail = command.email();
        if (customerEmail.value().isBlank()) {
            throw new ValidationException("There is no customer founded with this email: " + customerEmail);
        }
        customerRepository.deleteByEmail(customerEmail);
    }
}
