package dev.christopherbell.libs.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class InvalidTokenExceptionTest {

  @Test
  public void testInvalidTokenException() {
    var exception = InvalidTokenException.builder().build();

    assertNotNull(exception);
  }

  @Test
  public void testInvalidTokenExceptionWithMessage() {
    var exception = new InvalidTokenException("Invalid Token");

    assertNotNull(exception);
    assertEquals("Invalid Token", exception.getMessage());
  }

  @Test
  public void testInvalidTokenExceptionWithMessageAndException() {
    var exception = new InvalidTokenException("Invalid Token", new Exception("Another Exception"));

    assertNotNull(exception);
    assertEquals("Invalid Token", exception.getMessage());
    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }

  @Test
  public void testInvalidTokenExceptionWithException() {
    var exception = new InvalidTokenException(new Exception("Another Exception"));

    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }
}