package dev.christopherbell.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user account in the system.
 *
 * <p>
 * This class is mapped to the "accounts" collection in MongoDB.
 * It includes fields for user information, authentication details,
 * and account status. Sensitive information like password hash and
 * salt are included here but should be handled carefully in application logic.
 * </p>
 */
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Document("accounts")
public class Account {
  public static final String PARTITION_KEY = "ACCOUNT";
  public static final String PROPERTY_APPROVED_BY = "approvedBy";
  public static final String PROPERTY_CREATED_ON = "createdOn";
  public static final String PROPERTY_EMAIL = "email";
  public static final String PROPERTY_FIRST_NAME = "firstName";
  public static final String PROPERTY_IS_APPROVED = "isApproved";
  public static final String PROPERTY_LAST_LOGIN_ON = "lastLoginOn";
  public static final String PROPERTY_LAST_NAME = "lastName";
  public static final String PROPERTY_LOGIN_TOKEN = "loginToken";
  public static final String PROPERTY_PASSWORD_HASH = "passwordHash";
  public static final String PROPERTY_PASSWORD_SALT = "passwordSalt";
  public static final String PROPERTY_ROLE = "role";
  public static final String PROPERTY_USERNAME = "username";

  @Id
  private String id;
  private String approvedBy;
  private Instant createdOn;

  @Indexed(unique = true)
  private String email;
  private String firstName;
  private Boolean isApproved;
  private UUID inviteCode;
  private UUID inviteCodeOwner;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssss'Z'", timezone = "UTC")
  private Instant lastLoginOn;
  private String lastName;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssss'Z'", timezone = "UTC")
  private Instant lastUpdatedOn;
  private String loginToken;
  private String passwordSalt;
  private String passwordHash;
  private Role role;
  private AccountStatus status;
  @Indexed(unique = true)
  private String username;
}
