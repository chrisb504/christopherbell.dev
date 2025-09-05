package dev.christopherbell.libs.common.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsernameSanitizerTest {

  @Test
  @DisplayName("Returns same value when input is already valid and clean")
  public void testSanitize_whenAlreadyValid() {
    String input = UsernameStub.getValidAlreadyCleanStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals(input, result);
  }

  @Test
  @DisplayName("Trims leading/trailing whitespace")
  public void testSanitize_whenWhitespaceOnEdges() {
    String input = UsernameStub.getWithLeadingTrailingWhitespaceStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("user", result);
  }

  @Test
  @DisplayName("Removes invalid characters (symbols, spaces, etc.)")
  public void testSanitize_whenInvalidCharactersPresent() {
    String input = UsernameStub.getWithInvalidCharsStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("uername", result);
  }

  @Test
  @DisplayName("Collapses duplicate specials (., _, -) into a single underscore")
  public void testSanitize_whenDuplicateSpecialsPresent() {
    String input = UsernameStub.getWithDuplicateSpecialsStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("a_b", result);
  }

  @Test
  @DisplayName("Throws when length is below minimum (raw)")
  public void testSanitize_whenBelowMinLengthRaw() {
    String input = UsernameStub.getBelowMinRawStub();
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> UsernameSanitizer.sanitize(input));
    assertTrue(ex.getMessage().contains("between 3 and 32"));
  }

  @Test
  @DisplayName("Throws when length becomes below minimum after sanitization")
  public void testSanitize_whenBelowMinAfterSanitization() {
    String input = UsernameStub.getBelowMinAfterSanitizationStub();
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> UsernameSanitizer.sanitize(input));
    assertTrue(ex.getMessage().contains("between 3 and 32"));
  }

  @Test
  @DisplayName("Throws when only invalid characters remain (empty after sanitize)")
  public void testSanitize_whenOnlyInvalidCharsRemain() {
    String input = UsernameStub.getOnlyInvalidCharsStub();
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> UsernameSanitizer.sanitize(input));
    assertTrue(ex.getMessage().contains("between 3 and 32"));
  }

  @Test
  @DisplayName("Accepts value exactly at minimum length")
  public void testSanitize_whenAtMinimumLength() {
    String input = UsernameStub.getAtMinLengthStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("abc", result);
  }

  @Test
  @DisplayName("Accepts value exactly at maximum length")
  public void testSanitize_whenAtMaximumLength() {
    String input = UsernameStub.getAtMaxLengthStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals(input, result);
  }

  @Test
  @DisplayName("Throws when value exceeds maximum length")
  public void testSanitize_whenAboveMaximumLength() {
    String input = UsernameStub.getAboveMaxLengthStub();
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> UsernameSanitizer.sanitize(input));
    assertTrue(ex.getMessage().contains("between 3 and 32"));
  }

  @Test
  @DisplayName("Removes all non-ASCII letters/digits/safe specials (unicode & emoji stripped)")
  public void testSanitize_whenUnicodeAndEmojiPresent() {
    String input = UsernameStub.getWithUnicodeAndEmojiStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("name", result);
  }

  @Test
  @DisplayName("Collapses mixed runs of specials to a single underscore")
  public void testSanitize_whenMixedRunsOfSpecials() {
    String input = UsernameStub.getWithMixedRunsOfSpecialsStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("a_b", result);
  }

  @Test
  @DisplayName("Removes internal spaces when result still meets min length")
  public void testSanitize_whenInternalSpacesRemainLengthValid() {
    String input = UsernameStub.getWithInternalSpacesValidStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("abc", result);
  }

  @Test
  @DisplayName("Throws when internal spaces removal causes length < 3")
  public void testSanitize_whenInternalSpacesCauseTooShort() {
    String input = UsernameStub.getWithInternalSpacesTooShortStub();
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> UsernameSanitizer.sanitize(input));
    assertTrue(ex.getMessage().contains("between 3 and 32"));
  }

  @Test
  @DisplayName("Collapses leading/trailing runs of specials but keeps single specials")
  public void testSanitize_whenLeadingTrailingSpecialRuns() {
    String input = UsernameStub.getWithLeadingTrailingSpecialRunsStub();
    String result = UsernameSanitizer.sanitize(input);
    assertEquals("_user_", result);
  }

  @Test
  @DisplayName("Throws NullPointerException with correct message when input is null")
  public void testSanitize_whenInputIsNull() {
    NullPointerException npe =
        assertThrows(NullPointerException.class, () -> UsernameSanitizer.sanitize(null));
    assertEquals("Username cannot be null", npe.getMessage());
  }
}
