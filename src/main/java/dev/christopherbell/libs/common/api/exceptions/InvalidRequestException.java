package dev.christopherbell.libs.common.api.exceptions;

import lombok.Builder;

/**
 * Exception to throw if there is missing or incorrect data for a request.
 */
@Builder
public class InvalidRequestException extends Exception {

  public InvalidRequestException() {
    super();
  }

  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidRequestException(String message) {
    super(message);
  }

  public InvalidRequestException(Throwable cause) {
    super(cause);
  }
}