package dev.christopherbell.libs.common.api.exceptions;

import lombok.Builder;

/**
 * Exception to throw if the request token is not valid.
 */
@Builder
public class InvalidTokenException extends Exception {

  public InvalidTokenException() {
    super();
  }

  public InvalidTokenException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidTokenException(String message) {
    super(message);
  }

  public InvalidTokenException(Throwable cause) {
    super(cause);
  }
}