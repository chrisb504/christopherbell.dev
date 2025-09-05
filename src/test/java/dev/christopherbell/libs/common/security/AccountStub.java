package dev.christopherbell.libs.common.security;

import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.Role;

/**
 * Test stub for Account DTO.
 * Minimal fields + setters/getters needed by PasswordUtils.
 * (No mocks; concrete stub per rules.)
 */
public class AccountStub extends Account {
  private String passwordSalt;
  private String passwordHash;

  public static AccountStub getEmptyAccountStub() {
    return new AccountStub();
  }

  @Override
  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  @Override
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  // Adjust to your real builder/ctor. Example assumes Lombok-style builder + setters.
  public static Account getActiveAdminApprovedAccountStub() {
    return Account.builder()
        .id("acc-123")
        .role(Role.ADMIN)
        .isApproved(true)
        .build();
  }

  public static Account getActiveUserApprovedAccountStub() {
    return Account.builder()
        .id("acc-789")
        .role(Role.USER)
        .isApproved(true)
        .build();
  }

  public static Account getActiveUserUnapprovedAccountStub() {
    return Account.builder()
        .id("acc-456")
        .role(Role.USER)
        .isApproved(false)
        .build();
  }

  public static Account getAccountWithSaltHashApprovedUserStub(String salt, String hash) {
    Account a = Account.builder()
        .id("acc-999")
        .role(Role.USER)
        .isApproved(true)
        .build();
    a.setPasswordSalt(salt);
    a.setPasswordHash(hash);
    return a;
  }
}
