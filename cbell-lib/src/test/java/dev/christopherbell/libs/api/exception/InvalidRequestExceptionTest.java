package dev.christopherbell.libs.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class InvalidRequestExceptionTest {

  @Test
  public void testInvalidRequestException() {
    var exception = InvalidRequestException.builder().build();

    assertNotNull(exception);
  }

  @Test
  public void testInvalidRequestExceptionWithMessage() {
    var exception = new InvalidRequestException("Invalid Request");

    assertNotNull(exception);
    assertEquals("Invalid Request", exception.getMessage());
  }

  @Test
  public void testInvalidRequestExceptionWithMessageAndException() {
    var exception = new InvalidRequestException("Invalid Request", new Exception("Another Exception"));

    assertNotNull(exception);
    assertEquals("Invalid Request", exception.getMessage());
    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }

  @Test
  public void testInvalidRequestExceptionWithException() {
    var exception = new InvalidRequestException(new Exception("Another Exception"));

    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }
}