package dev.christopherbell.thevoid.testutils;

import dev.christopherbell.thevoid.models.db.CryEntity;
import dev.christopherbell.thevoid.models.db.VoidRoleEntity;
import dev.christopherbell.thevoid.models.db.account.AccountDetailsEntity;
import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import dev.christopherbell.thevoid.models.db.account.AccountSecurityEntity;
import dev.christopherbell.thevoid.models.domain.Address;
import dev.christopherbell.thevoid.models.domain.account.Account;
import dev.christopherbell.thevoid.models.domain.account.AccountDetails;
import dev.christopherbell.thevoid.models.domain.account.AccountSecurity;
import java.util.List;

public class AccountStub {

  public static Account getAccount() {
    return Account.builder()
        .username("NewUser")
        .accountDetails(getAccountDetails())
        .accountSecurity(getAccountSecurity())
        .build();
  }

  public static AccountDetails getAccountDetails() {
    return AccountDetails.builder()
        .firstName("Test")
        .lastName("User")
        .phoneNumber("555-555-5555")
        .address(getAddress())
        .build();
  }

  public static AccountEntity getAccountEntity() {

    return AccountEntity.builder()
        .accountDetailsEntity(getAccountDetailsEntity())
        .accountSecurityEntity(getAccountSecurityEntity())
        .voidRoleEntity(getVoidRoleEntity())
        .cries(List.of(getCryEntity()))
        .build();
  }

  public static CryEntity getCryEntity() {
    return CryEntity.builder()
        .text("testCry")
        .isRootCry(true)
        .expirationDate("1234")
        .id(1L)
        .lastAmplifiedDate("1234")
        .creationDate("1234")
        .build();
  }

  public static AccountDetailsEntity getAccountDetailsEntity() {
    return AccountDetailsEntity.builder()
        .id(1L)
        .creationDate("12345")
        .lastLoginDate("12345")
        .lastName("testLastName")
        .phoneNumber("1234567890")
        .firstName("testFirstName")
        .build();
  }

  public static AccountSecurityEntity getAccountSecurityEntity() {
    return AccountSecurityEntity.builder()
        .loginToken("123456")
        .email("test@test.com")
        .password("terriblePassword")
        .id(1L)
        .build();
  }

  public static VoidRoleEntity getVoidRoleEntity() {
    return VoidRoleEntity.builder()
        .role("VOID_LORD")
        .id(1L)
        .build();
  }

  public static AccountSecurity getAccountSecurity() {
    return AccountSecurity.builder()
        .email("test@test.com")
        .password("thebestpasswordever!")
        .build();
  }

  public static Address getAddress() {
    return Address.builder()
        .city("TestCity")
        .state("TestState")
        .country("TestCountry")
        .build();
  }

  public static String getClientId() {
    return "void-client";
  }
}
