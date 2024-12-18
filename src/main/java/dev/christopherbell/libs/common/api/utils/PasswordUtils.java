package dev.christopherbell.libs.common.api.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;

public final class PasswordUtils {

  // Define constants
  private static final int SALT_LENGTH = 16;  // Length of the salt in bytes
  private static final int HASH_ITERATIONS = 65536; // Number of iterations
  private static final int HASH_KEY_LENGTH = 256; // Derived key length in bits

  @Value("${api.util.salt-password}")
  private static String SALT_PASSWORD;

  private PasswordUtils() {}

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
  public static String hashPassword(String password, String salt) throws Exception {
    PBEKeySpec spec = new PBEKeySpec(
        password.toCharArray(), Base64.getDecoder().decode(salt), HASH_ITERATIONS, HASH_KEY_LENGTH);
    SecretKeyFactory factory = SecretKeyFactory.getInstance(SALT_PASSWORD);
    byte[] hash = factory.generateSecret(spec).getEncoded();
    return Base64.getEncoder().encodeToString(hash);
  }

  /**
   * Verifies a password against a stored hash.
   */
  public static boolean verifyPassword(String password, String salt, String storedHash) throws Exception {
    String computedHash = hashPassword(password, salt);
    return computedHash.equals(storedHash);
  }
}
