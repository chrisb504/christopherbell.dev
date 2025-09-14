package dev.christopherbell.account;

import dev.christopherbell.libs.api.model.Message;
import dev.christopherbell.libs.api.model.Response;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler for AccountController to handle specific account-related exceptions
 * and return appropriate HTTP responses.
 */
@ControllerAdvice
@Slf4j
public class AccountControllerExceptionHandler {
  private final static String ACCOUNT_NOT_ACTIVE_CODE = "005";

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
}
