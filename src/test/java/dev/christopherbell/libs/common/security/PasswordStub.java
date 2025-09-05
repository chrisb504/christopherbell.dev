package dev.christopherbell.libs.common.security;

import java.util.Base64;

public class PasswordStub {

  // ---- Passwords ----
  public static String getPasswordStub() {
    return "CorrectHorseBatteryStaple!123";
  }

  public static String getAnotherPasswordStub() {
    return "Tr0ub4dor&3";
  }

  public static String getEmptyPasswordStub() {
    return "";
  }

  // ---- Salts ----
  /** Generates a fresh, valid random salt via the production API. */
  public static String getRandomSaltStub() {
    return PasswordUtil.generateSalt();
  }

  /** A distinct fresh salt for inequality tests. */
  public static String getAnotherRandomSaltStub() {
    return PasswordUtil.generateSalt();
  }

  /** Not Base64 – used to assert IllegalArgumentException from Base64 decoder. */
  public static String getInvalidBase64SaltStub() {
    return "***not-base64***";
  }

  /**
   * A valid, deterministic salt for repeatability in specific tests (16 bytes => 24 Base64 chars).
   */
  public static String getDeterministicSaltStub() {
    // 16 bytes of 0x01..0x10 for stable regression checks.
    byte[] salt =
        new byte[] {
          0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
          0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10
        };
    return Base64.getEncoder().encodeToString(salt);
  }
}
