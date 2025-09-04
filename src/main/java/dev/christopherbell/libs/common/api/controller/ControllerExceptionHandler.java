package dev.christopherbell.libs.common.api.controller;

import dev.christopherbell.account.AccountNotActiveException;
import dev.christopherbell.libs.common.api.model.Message;
import dev.christopherbell.libs.common.api.model.Response;
import dev.christopherbell.libs.common.api.exception.ResourceNotFoundException;
import dev.christopherbell.libs.common.api.exception.InvalidRequestException;
import dev.christopherbell.libs.common.api.exception.InvalidTokenException;
import dev.christopherbell.libs.common.api.exception.ResourceExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Global exception handler for controllers to handle specific exceptions and return appropriate
 * HTTP responses.
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
  private final static String ACCOUNT_NOT_ACTIVE_CODE = "005";
  private final static String ACCOUNT_NOT_FOUND_CODE = "002";
  private final static String ACCOUNT_USER_NAME_EXISTS_CODE = "003";
  private final static String INVALID_REQUEST_CODE = "001";
  private final static String INVALID_TOKEN_CODE = "004";

  /**
   * Handles AccountNotActiveException and returns a 400 Bad Request response with an error message.
   *
   * @param e the AccountNotActiveException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler({AccountNotActiveException.class})
  public ResponseEntity<Response<?>> handleAccountNotActiveException(AccountNotActiveException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        Response.builder()
            .messages(List.of(Message.builder()
                .code(ACCOUNT_NOT_ACTIVE_CODE)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles InvalidRequestException and returns a 400 Bad Request response with an error message.
   *
   * @param e the InvalidRequestException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler({InvalidRequestException.class})
  public ResponseEntity<Response<?>> handleInvalidRequestException(InvalidRequestException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        Response.builder()
            .messages(List.of(Message.builder()
                .code(INVALID_REQUEST_CODE)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles ResourceNotFoundException and returns a 404 Not Found response with an error message.
   *
   * @param e the ResourceNotFoundException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<Response<?>> handleResourceNotFoundException(ResourceNotFoundException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        Response.builder()
            .messages(List.of(Message.builder()
                .code(ACCOUNT_NOT_FOUND_CODE)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build(), HttpStatus.NOT_FOUND);
  }

  /**
   * Handles ResourceExistsException and returns a 400 Bad Request response with an error message.
   *
   * @param e the ResourceExistsException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler({ResourceExistsException.class})
  public ResponseEntity<Response<?>> handleResourceExistsException(ResourceExistsException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        Response.builder()
            .messages(List.of(Message.builder()
                .code(ACCOUNT_USER_NAME_EXISTS_CODE)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles InvalidTokenException and returns a 401 Unauthorized response with an error message.
   *
   * @param e the InvalidTokenException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler({InvalidTokenException.class})
  public ResponseEntity<Response<?>> handleInvalidTokenException(InvalidTokenException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(
        Response.builder()
            .messages(List.of(Message.builder()
                .code(INVALID_TOKEN_CODE)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build(), HttpStatus.UNAUTHORIZED);
  }
}
