package dev.amirgol.springcqrs.application.dto;

import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(
        @NotNull
        @Schema(description = "Customer unique identifier", example = "c1a6c72a-5d98-4bca-90d6-1c9d0e5ee9b0")
        CustomerId id,

        @NotNull
        @Schema(description = "Full name of the customer", example = "Amir Golmoradi")
        FullName fullName,

        @NotNull
        @jakarta.validation.constraints.Email
        @Schema(description = "Customer email address", example = "ahgolmoradi12@gmail.com")
        Email email
) {
}
