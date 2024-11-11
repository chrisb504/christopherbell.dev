package dev.christopherbell.libs.common.api.exceptions;

import lombok.Builder;

/**
 * Exception to throw if we cannot find an account for a request.
 */
@Builder
public class NotFoundException extends Exception {

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }
}