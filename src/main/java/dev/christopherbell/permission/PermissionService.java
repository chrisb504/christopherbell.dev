package dev.christopherbell.permission;

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
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PermissionService {

  private static final String SECRET_KEY = generateKey() ; // Replace with a secure key
  private static final long EXPIRATION_TIME = 3600_000; // 1 hour in milliseconds
  private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

  public static String generateToken(String username) {
    var claims = new HashMap<String, Object>();
    claims.put("role", "USER"); // You can add more claims as needed

    return Jwts.builder()
        .claim("role", "USER") // Add custom claims explicitly
        .id(UUID.randomUUID().toString())
        .subject(username)  // Add the username as the subject
        .issuedAt(new Date()) // Set the token issuance time
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration time
        .signWith(KEY) // Use the secure key for signing
        .compact();
  }

  public static Claims validateToken(String token) {
    return Jwts.parser()
        .setSigningKey(KEY)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public static String generateKey() {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      keyGen.init(256); // Use 256-bit key
      SecretKey secretKey = keyGen.generateKey();

      // Print base64-encoded key
      String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
      System.out.println("Generated Key: " + base64Key);
      return base64Key;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return "";
    }
  }
}
