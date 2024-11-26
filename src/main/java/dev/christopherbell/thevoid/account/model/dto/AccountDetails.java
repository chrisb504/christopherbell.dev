package dev.christopherbell.thevoid.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AccountDetails {

  private Long id;
  private String creationDate; // The date that the user joined the platform
  private String firstName;
  private String lastName;
  private String lastLoginDate;
  private Address address; // Of course this shouldn't be a String. We do need to understand where the user is posting from
  private String phoneNumber;
}
