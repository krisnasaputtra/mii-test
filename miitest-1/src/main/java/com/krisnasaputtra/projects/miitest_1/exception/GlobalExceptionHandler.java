package com.krisnasaputtra.projects.miitest_1.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.krisnasaputtra.projects.miitest_1.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

/** Global exception handler for consistent error responses across the application. */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        /** Handles external API errors: 4xx as BAD_GATEWAY, 5xx as SERVICE_UNAVAILABLE. */
        @ExceptionHandler(ExternalApiException.class)
        public ResponseEntity<ApiResponse> handleExternalApiException(
                        ExternalApiException ex) {
                log.error("External API error occurred", ex);

                String message = ex.getMessage();
                HttpStatus status = message.contains("Client error") 
                        ? HttpStatus.BAD_GATEWAY 
                        : HttpStatus.SERVICE_UNAVAILABLE;

                ApiResponse response = ApiResponse.builder()
                                .code("0")
                                .message("External service temporarily unavailable. Please try again later.")
                                .build();

                return ResponseEntity
                                .status(status)
                                .body(response);
        }

        /** Handles validation errors from Spring Boot request body validation. */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex) {
                String errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                                .collect(Collectors.joining(", "));

                log.warn("Validation failed: {}", errors);

                ApiResponse response = ApiResponse.builder()
                                .code("0")
                                .message("Validation failed: " + errors)
                                .build();

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(response);
        }

        /** Handles illegal argument errors for invalid data values. */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ApiResponse> handleIllegalArgument(
                        IllegalArgumentException ex) {
                log.warn("Invalid argument: {}", ex.getMessage());

                ApiResponse response = ApiResponse.builder()
                                .code("0")
                                .message(ex.getMessage())
                                .build();

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(response);
        }

        /** Catches all unhandled exceptions for graceful error responses. */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
                log.error("Unexpected error occurred", ex);

                ApiResponse response = ApiResponse.builder()
                                .code("0")
                                .message("An unexpected error occurred")
                                .build();

                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(response);
        }
}