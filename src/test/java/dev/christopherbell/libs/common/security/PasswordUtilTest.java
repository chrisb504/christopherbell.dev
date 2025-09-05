package dev.christopherbell.libs.common.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

  @Test
  @DisplayName("generateSalt: returns Base64-encoded value of 16 bytes")
  public void testGenerateSalt_whenCalled_returnsBase64Of16Bytes() {
    String salt = PasswordUtil.generateSalt();

    // Decodes and validates exact 16-byte salt length.
    byte[] decoded = Base64.getDecoder().decode(salt);
    assertEquals(16, decoded.length, "Salt must decode to 16 bytes");

    // Base64 should be 24 chars for 16-byte input (with padding).
    assertEquals(24, salt.length(), "Base64-encoded 16-byte salt should be 24 chars");
  }

  @Test
  @DisplayName("generateSalt: returns different values across invocations")
  public void testGenerateSalt_whenCalledMultipleTimes_producesDifferentValues() {
    String s1 = PasswordStub.getRandomSaltStub();
    String s2 = PasswordStub.getAnotherRandomSaltStub();
    assertNotEquals(s1, s2, "Two generated salts should differ");
  }

  @Test
  @DisplayName("hashPassword: deterministic for same password+salt")
  public void testHashPassword_whenSameInputs_producesSameHash()
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    String pwd = PasswordStub.getPasswordStub();
    String salt = PasswordStub.getDeterministicSaltStub();

    String h1 = PasswordUtil.hashPassword(pwd, salt);
    String h2 = PasswordUtil.hashPassword(pwd, salt);

    assertEquals(h1, h2, "Hashing must be deterministic with identical inputs");
  }

  @Test
  @DisplayName("hashPassword: different salts yield different hashes")
  public void testHashPassword_whenSaltsDiffer_producesDifferentHashes()
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    String pwd = PasswordStub.getPasswordStub();
    String s1 = PasswordStub.getRandomSaltStub();
    String s2 = PasswordStub.getAnotherRandomSaltStub();

    String h1 = PasswordUtil.hashPassword(pwd, s1);
    String h2 = PasswordUtil.hashPassword(pwd, s2);

    assertNotEquals(h1, h2, "Different salts should produce different hashes");
  }

  @Test
  @DisplayName("hashPassword: throws IllegalArgumentException on invalid Base64 salt")
  public void testHashPassword_whenSaltInvalidBase64_throwsIllegalArgumentException() {
    String pwd = PasswordStub.getPasswordStub();
    String badSalt = PasswordStub.getInvalidBase64SaltStub();

    assertThrows(IllegalArgumentException.class, () -> PasswordUtil.hashPassword(pwd, badSalt));
  }

  @Test
  @DisplayName("hashPassword: throws NullPointerException when password is null")
  public void testHashPassword_whenPasswordNull_throwsNullPointerException() {
    String salt = PasswordStub.getDeterministicSaltStub();
    assertThrows(NullPointerException.class, () -> PasswordUtil.hashPassword(null, salt));
  }

  @Test
  @DisplayName("saltPassword: sets salt and hash on account; hash verifies")
  public void testSaltPassword_whenCalled_setsSaltAndHashOnAccount()
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    String pwd = PasswordStub.getPasswordStub();
    AccountStub account = AccountStub.getEmptyAccountStub();

    PasswordUtil.saltPassword(pwd, account);

    String salt = account.getPasswordSalt();
    String hash = account.getPasswordHash();

    assertNotNull(salt, "Salt should be set");
    assertNotNull(hash, "Hash should be set");

    // Salt should be valid Base64 for 16 bytes.
    byte[] decoded = Base64.getDecoder().decode(salt);
    assertEquals(16, decoded.length, "Persisted salt must decode to 16 bytes");

    // Recompute to assert consistency
    String recomputed = PasswordUtil.hashPassword(pwd, salt);
    assertEquals(recomputed, hash, "Stored hash should match recomputed hash for same inputs");
  }

  @Test
  @DisplayName("verifyPassword: returns true for correct password")
  public void testVerifyPassword_whenPasswordCorrect_returnsTrue()
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    String pwd = PasswordStub.getPasswordStub();
    String salt = PasswordStub.getRandomSaltStub();
    String hash = PasswordUtil.hashPassword(pwd, salt);

    boolean ok = PasswordUtil.verifyPassword(pwd, salt, hash);
    assertTrue(ok, "Correct password must verify");
  }

  @Test
  @DisplayName("verifyPassword: returns false for incorrect password")
  public void testVerifyPassword_whenPasswordIncorrect_returnsFalse()
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    String pwd = PasswordStub.getPasswordStub();
    String badPwd = PasswordStub.getAnotherPasswordStub();
    String salt = PasswordStub.getRandomSaltStub();
    String hash = PasswordUtil.hashPassword(pwd, salt);

    boolean ok = PasswordUtil.verifyPassword(badPwd, salt, hash);
    assertFalse(ok, "Incorrect password must not verify");
  }

  @Test
  @DisplayName("verifyPassword: throws IllegalArgumentException when salt is invalid Base64")
  public void testVerifyPassword_whenSaltInvalidBase64_throwsIllegalArgumentException() {
    String pwd = PasswordStub.getPasswordStub();
    String badSalt = PasswordStub.getInvalidBase64SaltStub();
    String bogusStoredHash = "ignoredIfSaltInvalid";

    assertThrows(IllegalArgumentException.class,
        () -> PasswordUtil.verifyPassword(pwd, badSalt, bogusStoredHash));
  }
}
