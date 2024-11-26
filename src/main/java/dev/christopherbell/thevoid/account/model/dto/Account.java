package dev.christopherbell.thevoid.account.model.dto;

import dev.christopherbell.thevoid.cry.model.Cry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Account {

  private AccountDetails accountDetails;
  private AccountSecurity accountSecurity;
  private List<Cry> cries;
  private Long id;
  private String username; // The user's account name
  private VoidRolesEnum voidRole; // This will represent what kind of account we are dealing with
  private List<Account> followers;
  private List<Account> following;
}
