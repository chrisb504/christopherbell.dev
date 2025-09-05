package dev.christopherbell.libs.common.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.christopherbell.libs.common.api.exception.InvalidRequestException;
import dev.christopherbell.libs.common.api.APIConstants;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class APIValidationUtilTest {

  private static final String TEST_CLIENT_ID = "testClientId";

  @Test
  public void testIsValidClientId_success() throws InvalidRequestException {
    var result = APIValidationUtil.isValidClientId(List.of(TEST_CLIENT_ID), TEST_CLIENT_ID);

    assertTrue(result);
  }

  @Test
  public void testIsValidClientId_failure_exceptionThrow() {

    var exception = assertThrows(InvalidRequestException.class, () -> {
      APIValidationUtil.isValidClientId(List.of(TEST_CLIENT_ID), "");
    });

    assertEquals(APIConstants.VALIDATION_BAD_CLIENT_ID, exception.getMessage());
  }

  @Test
  public void testIsValidResource_success() throws InvalidRequestException {
    var result = APIValidationUtil.isValidResource(APIConstants.VALIDATION_BAD_EMAIL, "test@test.com");

    assertTrue(result);
  }

  @ParameterizedTest
  @ValueSource(strings = {"", " "})
  public void testIsValidResource_failure(String resource) {

    var exception = assertThrows(InvalidRequestException.class, () -> {
      APIValidationUtil.isValidResource(APIConstants.VALIDATION_BAD_EMAIL, resource);
    });

    assertEquals(APIConstants.VALIDATION_BAD_EMAIL, exception.getMessage());
  }

  @Test
  public void testIsValidResource_failure_nullResource() {

    var exception = assertThrows(InvalidRequestException.class, () -> {
      APIValidationUtil.isValidResource(APIConstants.VALIDATION_BAD_EMAIL, null);
    });

    assertEquals(APIConstants.VALIDATION_BAD_EMAIL, exception.getMessage());
  }
}