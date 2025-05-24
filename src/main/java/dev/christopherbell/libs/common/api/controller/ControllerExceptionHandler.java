package dev.christopherbell.libs.common.api.controller;

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

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
  private final static String INVALID_REQUEST_CODE = "001";
  private final static String ACCOUNT_NOT_FOUND_CODE = "002";
  private final static String ACCOUNT_USER_NAME_EXISTS_CODE = "003";
  private final static String INVALID_TOKEN_CODE = "004";


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
