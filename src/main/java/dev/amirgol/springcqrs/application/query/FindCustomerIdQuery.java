package dev.amirgol.springcqrs.application.query;

import dev.amirgol.springcqrs.application.dto.CustomerResponse;
import dev.amirgol.springcqrs.core.query.Query;
import dev.amirgol.springcqrs.domain.value_object.CustomerId;
import jakarta.validation.constraints.NotNull;

public record FindCustomerIdQuery(
        @NotNull
        CustomerId id
) implements Query<CustomerResponse> {
}
