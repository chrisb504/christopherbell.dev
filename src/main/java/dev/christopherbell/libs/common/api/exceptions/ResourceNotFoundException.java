package dev.christopherbell.libs.common.api.exceptions;

import lombok.Builder;

/**
 * Exception to throw if we cannot find an account for a request.
 */
@Builder
public class ResourceNotFoundException extends Exception {

  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}