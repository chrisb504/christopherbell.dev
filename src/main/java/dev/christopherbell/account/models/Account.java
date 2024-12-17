package dev.christopherbell.account.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

  public static final String PARTITION_KEY = "ACCOUNT";
  private String rowKey; // This is the user's email.

  private UUID approvedBy;
  private Instant createdOn;
  private String email;
  private Boolean isApproved;
  private UUID inviteCode;
  private UUID inviteCodeOwner;
  private Instant lastLoginOn;
  private String loginToken;
  private String name;
  private String password;
  private Role role;

  public static final String PROPERTY_APPROVED_BY = "approvedBy";
  public static final String PROPERTY_CREATED_ON = "createdOn";
  public static final String PROPERTY_EMAIL = "email";
  public static final String PROPERTY_IS_APPROVED = "isApproved";
  public static final String PROPERTY_LAST_LOGIN_ON = "lastLoginOn";
  public static final String PROPERTY_LOGIN_TOKEN = "loginToken";
  public static final String PROPERTY_NAME = "name";
  public static final String PROPERTY_PASSWORD = "password";
  public static final String PROPERTY_ROLE = "role";
}
