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
 * Global controller advice translating exceptions into consistent API responses.
 *
 * <p>Builds {@link Response} envelopes with {@link Message} entries and
 * appropriate HTTP statuses for common error types.</p>
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
   * Fallback handler for unanticipated exceptions. Returns HTTP 500 with a generic error message.
   *
   * @param e the exception
   * @return a {@link Response} with {@code success=false} and a single error {@link Message}
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
   * Handles {@link ResourceExistsException}. Returns HTTP 409 with error details.
   *
   * @param e the exception
   * @return a {@link Response} with {@code success=false} and an error {@link Message}
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
   * Handles {@link ResourceNotFoundException}. Returns HTTP 404 with error details.
   *
   * @param e the exception
   * @return a {@link Response} with {@code success=false} and an error {@link Message}
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
   * Handles {@link InvalidRequestException}. Returns HTTP 400 with error details.
   *
   * @param e the exception
   * @return a {@link Response} with {@code success=false} and an error {@link Message}
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
   * Handles {@link InvalidTokenException}. Returns HTTP 401 with error details.
   *
   * @param e the exception
   * @return a {@link Response} with {@code success=false} and an error {@link Message}
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
