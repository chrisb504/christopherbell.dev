package dev.christopherbell.libs.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ResourceExistsExceptionTest {

  @Test
  public void testResourceExistsException() {
    var exception = ResourceExistsException.builder().build();

    assertNotNull(exception);
  }

  @Test
  public void testResourceExistExceptionWithMessage() {
    var exception = new ResourceExistsException("Resource Not Found");

    assertNotNull(exception);
    assertEquals("Resource Not Found", exception.getMessage());
  }

  @Test
  public void testResourceExistsExceptionWithMessageAndException() {
    var exception = new ResourceExistsException("Resource Not Found", new Exception("Another Exception"));

    assertNotNull(exception);
    assertEquals("Resource Not Found", exception.getMessage());
    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }

  @Test
  public void testResourceExistsExceptionWithException() {
    var exception = new ResourceExistsException(new Exception("Another Exception"));

    assertNotNull(exception.getCause());
    assertEquals("Another Exception", exception.getCause().getMessage());
  }
}