package dev.amirgol.springcqrs.presentation;

import dev.amirgol.springcqrs.application.dto.ErrorResponse;
import dev.amirgol.springcqrs.core.exception.InternalServerException;
import dev.amirgol.springcqrs.core.exception.ResourceNotFoundException;
import dev.amirgol.springcqrs.core.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException ex) {
        log.error("Caught StorageException", ex);

        var errorResponse = ErrorResponse
                .builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Unable to save or retrieve resources at this time.")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        log.error("Caught NotFoundException", ex);
        var errorResponse = ErrorResponse
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message("Unable to find resource at this time.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        log.error("Caught ValidationException", ex);
        var errorResponse = ErrorResponse
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("There was an error processing your request.")
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
