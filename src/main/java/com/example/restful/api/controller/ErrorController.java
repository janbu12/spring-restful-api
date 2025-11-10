package com.example.restful.api.controller;

import com.example.restful.api.model.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * Global exception handler for REST API controllers.
 * <p>
 * This class is responsible for intercepting and handling exceptions that occur
 * during API execution, converting them into standardized {@link WebResponse} objects.
 * </p>
 *
 * <p>It handles common exceptions such as:</p>
 * <ul>
 *   <li>{@link ConstraintViolationException} - thrown when request validation fails.</li>
 *   <li>{@link ResponseStatusException} - thrown for manual HTTP status responses.</li>
 * </ul>
 *
 * <p>This helps ensure consistent error responses across the entire application.</p>
 *
 * @author Mizan
 * @version 1.0
 */
@RestControllerAdvice
public class ErrorController {

    /**
     * Handles {@link ConstraintViolationException}, typically thrown when
     * validation constraints (e.g., @NotNull, @Email) are violated in request data.
     *
     * @param exception the exception thrown due to validation failure.
     * @return a {@link ResponseEntity} containing a {@link WebResponse} with a descriptive error message
     *         and HTTP 400 (Bad Request) status.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder()
                        .message(exception.getMessage())
                        .build());
    }

    /**
     * Handles {@link ResponseStatusException}, which is often thrown manually
     * by developers to return a specific HTTP status and message.
     *
     * @param exception the {@link ResponseStatusException} thrown during request processing.
     * @return a {@link ResponseEntity} containing a {@link WebResponse} with the exception message
     *         and the corresponding HTTP status code.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponse.<String>builder()
                        .message(exception.getReason())
                        .build());
    }
}
