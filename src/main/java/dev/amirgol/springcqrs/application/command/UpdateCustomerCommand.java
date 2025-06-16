package dev.amirgol.springcqrs.application.command;

import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import dev.amirgol.springcqrs.domain.vo.Password;

public record UpdateCustomerCommand(
        Email email,
        FullName fullName,
        Password password
) {
}
