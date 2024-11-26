package dev.christopherbell.thevoid.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AccountSecurity {

  private Long id;
  private String email;
  private String loginToken;
  private String password;
}
