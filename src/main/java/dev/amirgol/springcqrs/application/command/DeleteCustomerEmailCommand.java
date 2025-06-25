package dev.amirgol.springcqrs.application.command;

import dev.amirgol.springcqrs.core.command.Command;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import dev.amirgol.springcqrs.domain.value_object.Email;

public record DeleteCustomerEmailCommand(
        Email email
) implements Command<CustomerId> {
}
