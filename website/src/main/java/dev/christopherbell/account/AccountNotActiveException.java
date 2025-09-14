package dev.christopherbell.account;

/**
 * Exception thrown when an operation is attempted on an account that is not active.
 */
public class AccountNotActiveException extends RuntimeException {
  public AccountNotActiveException(String message) {
    super(message);
  }
}
