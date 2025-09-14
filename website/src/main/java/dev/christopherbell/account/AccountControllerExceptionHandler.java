package dev.christopherbell.account;

import dev.christopherbell.libs.api.model.Message;
import dev.christopherbell.libs.api.model.Response;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler for AccountController to handle specific account-related exceptions
 * and return appropriate HTTP responses.
 */
@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccountControllerExceptionHandler {
  private final static String ACCOUNT_NOT_ACTIVE_CODE = "ACCOUNT_NOT_ACTIVE";

  /**
   * Handles AccountNotActiveException and returns a 400 Bad Request response with an error message.
   *
   * @param e the AccountNotActiveException
   * @return ResponseEntity with error details
   */
  @ExceptionHandler({AccountNotActiveException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<?> handleAccountNotActiveException(AccountNotActiveException e) {
    log.error(ACCOUNT_NOT_ACTIVE_CODE, e);
    return Response.builder()
            .messages(List.of(Message.builder()
                .code(ACCOUNT_NOT_ACTIVE_CODE)
                .description(e.getMessage())
                .build()))
            .success(false)
            .build();
  }
}
