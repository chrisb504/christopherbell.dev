package dev.christopherbell.libs.api.controller;

import dev.christopherbell.libs.api.model.Message;
import dev.christopherbell.libs.api.model.Response;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.libs.api.exception.InvalidTokenException;
import dev.christopherbell.libs.api.exception.ResourceExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for controllers to handle specific exceptions and return appropriate
 * HTTP responses.
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
  private final static String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
  private final static String RESOURCE_EXISTS = "RESOURCE_EXISTS";
  private final static String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
  private final static String INVALID_REQUEST = "INVALID_REQUEST";
  private final static String INVALID_TOKEN = "INVALID_TOKEN";

  /**
   * Handles generic exceptions and returns a 500 Internal Server Error response with a generic
   * error message.
   *
   * @param e the Exception
   * @return ResponseEntity with error details
   */
  public Response<?> handelGenericException(Exception e) {
    log.error(INTERNAL_SERVER_ERROR, e);
    return Response.builder()
            .messages(List.of(Message.builder()
                .code(INTERNAL_SERVER_ERROR)
                .description("An unexpected error occurred. Please try again later.")
                .build()))
            .success(false)
            .build();
  }

  /**
   * Handles ResourceExistsException and returns a 409 Conflict response with an error message.
   *
   * @param e the ResourceExistsException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(ResourceExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public Response<?> handleResourceExistsException(ResourceExistsException e) {
    log.error(RESOURCE_EXISTS, e);
    return Response.builder()
            .messages(List.of(Message.builder()
                .code(RESOURCE_EXISTS)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build();
  }

  /**
   * Handles ResourceNotFoundException and returns a 404 Not Found response with an error message.
   *
   * @param e the ResourceNotFoundException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Response<?> handleResourceNotFoundException(ResourceNotFoundException e) {
    log.error(RESOURCE_NOT_FOUND, e);
    return Response.builder()
        .messages(List.of(Message.builder()
            .code(RESOURCE_NOT_FOUND)
            .description(e.getMessage())
            .build()))
        .success(false)
        .build();
  }

  /**
   * Handles InvalidRequestException and returns a 400 Bad Request response with an error message.
   *
   * @param e the InvalidRequestException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(InvalidRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<?> handleInvalidRequestException(InvalidRequestException e) {
    log.error(INVALID_REQUEST, e);
    return Response.builder()
            .messages(List.of(Message.builder()
                .code(INVALID_REQUEST)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build();
  }

  /**
   * Handles InvalidTokenException and returns a 401 Unauthorized response with an error message.
   *
   * @param e the InvalidTokenException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(InvalidTokenException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Response<?> handleInvalidTokenException(InvalidTokenException e) {
    log.error(INVALID_TOKEN, e);
    return Response.builder()
            .messages(List.of(Message.builder()
                .code(INVALID_TOKEN)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build();
  }
}
