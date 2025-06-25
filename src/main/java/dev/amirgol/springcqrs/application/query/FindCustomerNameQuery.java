package dev.amirgol.springcqrs.application.query;

import dev.amirgol.springcqrs.application.dto.CustomerResponse;
import dev.amirgol.springcqrs.core.query.Query;
import dev.amirgol.springcqrs.domain.value_object.FullName;
import jakarta.validation.constraints.NotNull;

public record FindCustomerNameQuery(
        @NotNull
        FullName fullName
) implements Query<CustomerResponse> {
}
