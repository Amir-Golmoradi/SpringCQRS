package dev.amirgol.springcqrs.application.command;

import dev.amirgol.springcqrs.core.command.Command;
import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import dev.amirgol.springcqrs.domain.vo.Password;
import jakarta.validation.constraints.NotNull;

public record UpdateCustomerCommand(
        @NotNull
        CustomerId id,
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
