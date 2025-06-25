package dev.amirgol.springcqrs.application.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer httpStatus, String message) {
}
