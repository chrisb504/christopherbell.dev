package dev.christopherbell.libs.common.security;

import dev.christopherbell.account.model.AccountEntity;
import dev.christopherbell.libs.common.api.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PermissionService {

  private static final String SECRET_KEY = generateKey();
  private static final long EXPIRATION_TIME = 3600_000; // 1 hour in milliseconds
  private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

  /**
   * Generates a JWT token with key that was created on application startup.
   *
   * @param accountEntity - the account that will be getting the new token.
   * @return a JWT token in String format.
   */
  public static String generateToken(AccountEntity accountEntity) {
    var claims = new HashMap<String, Object>();
    claims.put(AccountEntity.PROPERTY_ROLE, accountEntity.getRole());

    return Jwts.builder()
        .claims(claims)
        .id(UUID.randomUUID().toString())
        .subject(accountEntity.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(KEY)
        .compact();
  }

  /**
   * Validates a given JWT token with the key that was generated on application start up.
   *
   * @param token - the given JWT.
   * @return the claims for that JWT.
   */
  public static Claims validateToken(String token) {
    var jwt = stripBearer(token);

    return Jwts.parser()
        .setSigningKey(KEY)
        .build()
        .parseClaimsJws(jwt)
        .getBody();
  }

  /**
   * Generates a key for creating JWTs on application startup.
   *
   * @return return key in string form.
   */
  public static String generateKey() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      keyGen.init(256);
      SecretKey secretKey = keyGen.generateKey();
      var base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
      System.out.println("Generated Key: " + base64Key);
      return base64Key;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return "";
    }
  }

  /**
   * Checks to see if a user has some required role in order to continue with their request.
   *
   * @param requiredRole - The role required for the request.
   * @return boolean on if the requester has the required role or not.
   */
  public boolean hasAuthority(String requiredRole) {
    try {
      var authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
        return false;
      }

      String token = (String) authentication.getCredentials();
      Claims claims = validateToken(token);
      String roles  = claims.get(AccountEntity.PROPERTY_ROLE, String.class);
      return roles != null && roles.contains(requiredRole);
    } catch (Exception e) {

      log.error("Error validating token or extracting claims: {}", e.getMessage(), e);
      return false; // Deny access on any error
    }
  }

  public static boolean isAccountApproved(AccountEntity accountEntity) throws InvalidTokenException {
    if (accountEntity.getIsApproved()) {
      return true;
    } else {
      throw new InvalidTokenException("Account is not approved.");
    }
  }

  /**
   * Removes the {@code "Bearer "} prefix from a JWT token string if present.
   * <p>
   * Many HTTP Authorization headers are formatted as
   * {@code "Authorization: Bearer <token>"}. This method ensures that only the
   * raw token value (the {@code <token>} part) is returned for downstream
   * parsing and validation.
   * </p>
   *
   * <p>If the input is {@code null}, this method returns {@code null}.
   * If the input does not start with the {@code "Bearer "} prefix,
   * the original string is returned unchanged.</p>
   *
   * @param token the full token string, possibly prefixed with {@code "Bearer "}
   * @return the token string without the {@code "Bearer "} prefix,
   *         or {@code null} if the input was {@code null}
   */
  private static String stripBearer(String token) {
    return token != null && token.startsWith("Bearer ") ? token.substring(7) : token;
  }
}
