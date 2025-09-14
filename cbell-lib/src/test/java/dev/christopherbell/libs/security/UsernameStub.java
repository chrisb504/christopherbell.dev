package dev.christopherbell.libs.security;

import lombok.experimental.UtilityClass;

/**
 * Utility class providing stub usernames for testing username sanitization logic.
 * These stubs cover various edge cases including valid usernames, usernames with
 * invalid characters, length constraints, and special character handling.
 */
@UtilityClass
public class UsernameStub {

  /**
   * A valid username that should remain unchanged after sanitization.
   * @return the stub username
   */
  public static String getValidAlreadyCleanStub() {
    return "user_name-01.test";
  }

  /**
   * Username with leading and trailing whitespace that should be trimmed.
   * @return the stub username
   */
  public static String getWithLeadingTrailingWhitespaceStub() {
    return "   user   ";
  }

  /**
   * Username with invalid characters (symbols, spaces, etc.) that should be removed.
   * @return the stub username
   */
  public static String getWithInvalidCharsStub() {
    return "u$er na!me";
  }

  /**
   * Username with consecutive special characters that should be collapsed into a single underscore.
   * @return the stub username
   */
  public static String getWithDuplicateSpecialsStub() {
    return "a..__--b";
  }

  /**
   * Username that is below the minimum length of 3 characters.
   * @return the stub username
   */
  public static String getBelowMinRawStub() {
    return "ab";
  }

  /**
   * Username that is above the minimum length of 3 characters but becomes too short after
   * sanitization.
   * @return the stub username
   */
  public static String getBelowMinAfterSanitizationStub() {
    return " a ";
  }

  /**
   * Username that contains only invalid characters and should be sanitized to an empty string.
   * @return the stub username
   */
  public static String getOnlyInvalidCharsStub() {
    return "  $$  ";
  }

  /**
   * Username that is exactly at the minimum length of 3 characters.
   * @return the stub username
   */
  public static String getAtMinLengthStub() {
    return "abc";
  }

  /**
   * Username that is exactly at the maximum length of 32 characters.
   * @return the stub username
   */
  public static String getAtMaxLengthStub() {
    return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
  }

  /**
   * Username that exceeds the maximum length of 32 characters.
   * @return the stub username
   */
  public static String getAboveMaxLengthStub() {
    return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
  }

  /**
   * Username with Unicode characters and emoji that should be removed.
   * @return the stub username
   */
  public static String getWithUnicodeAndEmojiStub() {
    return "用户😊name";
  }

  /**
   * Username with mixed runs of special characters that should be collapsed.
   * @return the stub username
   */
  public static String getWithMixedRunsOfSpecialsStub() {
    return "a..--__b";
  }

  /**
   * Username with internal spaces that becomes too short after sanitization.
   * @return the stub username
   */
  public static String getWithInternalSpacesTooShortStub() {
    return "a b";
  }

  /**
   * Username with internal spaces that should be preserved.
   * @return the stub username
   */
  public static String getWithInternalSpacesValidStub() {
    return "a b c";
  }

  /**
   * Username with leading and trailing special characters that should be removed.
   * @return the stub username
   */
  public static String getWithLeadingTrailingSpecialRunsStub() {
    return "__user__";
  }
}
