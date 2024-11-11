package dev.christopherbell.thevoid.utils.mappers;

import dev.christopherbell.thevoid.models.db.InviteCodeEntity;
import dev.christopherbell.thevoid.models.db.VoidRoleEntity;
import dev.christopherbell.thevoid.models.db.account.AccountDetailsEntity;
import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import dev.christopherbell.thevoid.models.db.CryEntity;
import dev.christopherbell.thevoid.models.db.account.AccountSecurityEntity;
import dev.christopherbell.thevoid.models.domain.InviteCode;
import dev.christopherbell.thevoid.models.domain.account.Account;
import dev.christopherbell.thevoid.models.domain.Cry;
import dev.christopherbell.thevoid.models.domain.account.AccountDetails;
import dev.christopherbell.thevoid.models.domain.account.AccountSecurity;
import dev.christopherbell.thevoid.models.domain.VoidRolesEnum;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

  AccountEntity mapToAccountEntity(Account account);

  Account mapToAccount(AccountEntity accountEntity);

  AccountDetailsEntity mapToAccountDetailsEntity(AccountDetails accountDetails);

  AccountDetails mapToAccountDetails(AccountDetailsEntity accountDetailsEntity);

  AccountSecurityEntity mapToAccountSecurityEntity(AccountSecurity accountSecurity);

  AccountSecurity mapToAccountSecurity(AccountSecurityEntity accountSecurityEntity);

  CryEntity mapToCryEntity(Cry cry);

  Cry mapToCry(CryEntity cryEntity);

  InviteCodeEntity mapToInviteCodeEntity(InviteCode inviteCode);

  InviteCode mapToInviteCode(InviteCodeEntity inviteCodeEntity);

  VoidRoleEntity mapToVoidRoleEntity(VoidRolesEnum voidRolesEnum);

  //VoidRolesEnum mapToVoidRoleEnum(VoidRoleEntity voidRoleEntity);
}
