package dev.christopherbell.account.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Account {
  private UUID approvedBy;
  private Instant createdOn;
  private String email;
  private String firstName;
  private Boolean isApproved;
  private String lastName;
  private UUID inviteCode;
  private UUID inviteCodeOwner;
  private Instant lastLoginOn;
  private String loginToken;
  private String password;
  private Role role;
  private String username;
}
