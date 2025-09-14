package dev.christopherbell.libs.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import lombok.experimental.UtilityClass;

/**
 * Utility class for password hashing and verification using PBKDF2 with HMAC-SHA256.
 */
@UtilityClass
public class PasswordUtil {
  private static final int SALT_LENGTH = 16;  // Length of the salt in bytes
  private static final int HASH_ITERATIONS = 65536; // Number of iterations
  private static final int HASH_KEY_LENGTH = 256; // Derived key length in bits

  /**
   * Generates a random salt.
   */
  public static String generateSalt() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[SALT_LENGTH];
    secureRandom.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }

  /**
   * Hashes a password with a given salt.
   */
  public static String hashPassword(String password, String salt)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    PBEKeySpec spec = new PBEKeySpec(
        password.toCharArray(), Base64.getDecoder().decode(salt), HASH_ITERATIONS, HASH_KEY_LENGTH);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    byte[] hash = factory.generateSecret(spec).getEncoded();
    return Base64.getEncoder().encodeToString(hash);
  }

  /**
   * Verifies a password against a stored hash.
   */
  public static boolean verifyPassword(String password, String salt, String storedHash)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    String computedHash = hashPassword(password, salt);
    return computedHash.equals(storedHash);
  }
}
