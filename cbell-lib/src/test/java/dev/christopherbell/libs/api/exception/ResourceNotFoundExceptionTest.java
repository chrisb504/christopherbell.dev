package dev.christopherbell.libs.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ResourceNotFoundExceptionTest {

  @Test
  public void testNotFoundException() {
    var exception = ResourceNotFoundException.builder().build();

    assertNotNull(exception);
  }

  @Test
  public void testNotFoundExceptionWithMessage() {
    var exception = new ResourceNotFoundException("Account Not Found");

    assertNotNull(exception);
    assertEquals("Account Not Found", exception.getMessage());
  }

  @Test
  public void testNotFoundExceptionWithMessageAndException() {
    var exception = new ResourceNotFoundException("Account Not Found", new Exception("Another Exception"));

    assertNotNull(exception);
    assertEquals("Account Not Found", exception.getMessage());
    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }

  @Test
  public void testNotFoundExceptionWithException() {
    var exception = new ResourceNotFoundException(new Exception("Another Exception"));

    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }
}