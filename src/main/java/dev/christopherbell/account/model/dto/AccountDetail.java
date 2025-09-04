package dev.christopherbell.account.model.dto;

import dev.christopherbell.account.model.AccountStatus;
import dev.christopherbell.account.model.Role;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning account details without sensitive information like password hash and salt.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AccountDetail {
  private String id;
  private String approvedBy;
  private Instant createdOn;
  private String email;
  private String firstName;
  private Boolean isApproved;
  private String lastName;
  private Instant lastLoginOn;
  private Instant lastUpdatedOn;
  private Role role;
  private AccountStatus status;
  private String username;
}
