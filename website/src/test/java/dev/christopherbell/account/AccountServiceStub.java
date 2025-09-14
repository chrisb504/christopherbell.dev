package dev.christopherbell.account;

import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.AccountStatus;
import dev.christopherbell.account.model.Role;
import dev.christopherbell.account.model.dto.AccountUpdateRequest;
import java.time.Instant;

/**
 * Stub factory for AccountService tests.
 */
public final class AccountServiceStub {
  public static final String ID = "acc-123";

  private AccountServiceStub() {}

  public static Account existingAccount() {
    return Account.builder()
        .id(ID)
        .email("old@example.com")
        .firstName("Old")
        .lastName("Name")
        .username("old_user")
        .role(Role.USER)
        .status(AccountStatus.INACTIVE)
        .isApproved(false)
        .createdOn(Instant.now())
        .lastUpdatedOn(Instant.now().minusSeconds(300))
        .build();
  }

  public static AccountUpdateRequest validUpdateRequestAllFields() {
    return AccountUpdateRequest.builder()
        .id(ID)
        .firstName("Chris")
        .lastName("Bell")
        .email("Chris@Example.com")
        .username("Chris.Bell  ")
        .role(Role.ADMIN)
        .status(AccountStatus.ACTIVE)
        .isApproved(true)
        .build();
  }

  public static AccountUpdateRequest partialUpdate_requestRoleOnly() {
    return AccountUpdateRequest.builder()
        .id(ID)
        .role(Role.ADMIN)
        .build();
  }

  public static AccountUpdateRequest partialUpdate_requestFlagsOnly() {
    return AccountUpdateRequest.builder()
        .id(ID)
        .status(AccountStatus.ACTIVE)
        .isApproved(true)
        .build();
  }

  public static AccountUpdateRequest noOpUpdate_onlyId() {
    return AccountUpdateRequest.builder()
        .id(ID)
        .build();
  }

  public static AccountUpdateRequest invalid_blankId() {
    return AccountUpdateRequest.builder().id("   ").build();
  }

  public static AccountUpdateRequest invalid_email() {
    return AccountUpdateRequest.builder()
        .id(ID)
        .email("bad@@example..com")
        .build();
  }

  public static AccountUpdateRequest invalid_username() {
    return AccountUpdateRequest.builder()
        .id(ID)
        .username("a")
        .build();
  }
}

