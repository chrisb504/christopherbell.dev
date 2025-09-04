package dev.christopherbell.libs.common.security;

import java.util.Objects;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

/**
 * Utility class for sanitizing and validating usernames.
 *
 * Rules:
 * - Only letters, digits, underscores, periods, and hyphens are allowed.
 * - Leading and trailing whitespace is removed.
 * - Usernames must be between 3 and 32 characters after sanitization.
 * - Consecutive underscores, periods, or hyphens are collapsed into a single character.
 */
@UtilityClass
public class UsernameSanitizer {
  private static final Pattern VALID_CHARS = Pattern.compile("[^a-zA-Z0-9._-]");
  private static final Pattern DUPLICATES = Pattern.compile("[._-]{2,}");
  private static final int MIN_LENGTH = 3;
  private static final int MAX_LENGTH = 32;

  /**
   * Sanitizes a raw username input.
   *
   * @param input raw username string
   * @return sanitized username
   * @throws IllegalArgumentException if the username cannot be sanitized
   */
  public static String sanitize(String input) {
    Objects.requireNonNull(input, "Username cannot be null");

    // Trim whitespace
    String sanitized = input.trim();

    // Remove invalid characters
    sanitized = VALID_CHARS.matcher(sanitized).replaceAll("");

    // Collapse duplicate special characters
    sanitized = DUPLICATES.matcher(sanitized).replaceAll("_");

    // Validate length
    if (sanitized.length() < MIN_LENGTH || sanitized.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Username must be between "
          + MIN_LENGTH + " and " + MAX_LENGTH + " characters");
    }

    return sanitized;
  }
}
