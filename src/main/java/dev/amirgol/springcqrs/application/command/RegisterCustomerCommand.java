package dev.amirgol.springcqrs.application.command;

import dev.amirgol.springcqrs.core.command.Command;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import dev.amirgol.springcqrs.domain.value_object.Email;
import dev.amirgol.springcqrs.domain.value_object.FullName;
import dev.amirgol.springcqrs.domain.value_object.Password;
import jakarta.validation.constraints.NotNull;

public record RegisterCustomerCommand(
        @NotNull
        @jakarta.validation.constraints.Email
        Email email,
        @NotNull
        FullName fullName,
        @NotNull
        Password password
) implements Command<CustomerId> {
    // Command that returns UUID of created customer
}
