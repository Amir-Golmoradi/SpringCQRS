package dev.amirgol.springcqrs.application.query;

import dev.amirgol.springcqrs.application.dto.CustomerResponse;
import dev.amirgol.springcqrs.core.query.Query;
import dev.amirgol.springcqrs.domain.value_object.Email;
import jakarta.validation.constraints.NotNull;

public record FindCustomerEmailQuery(
        @NotNull
        @jakarta.validation.constraints.Email
        Email email
) implements Query<CustomerResponse> {
}
