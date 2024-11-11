package dev.christopherbell.thevoid.utils.mappers;

import dev.christopherbell.thevoid.invite.model.InviteCodeEntity;
import dev.christopherbell.thevoid.account.model.entity.VoidRoleEntity;
import dev.christopherbell.thevoid.account.model.entity.AccountDetailsEntity;
import dev.christopherbell.thevoid.account.model.entity.AccountEntity;
import dev.christopherbell.thevoid.cry.model.CryEntity;
import dev.christopherbell.thevoid.account.model.entity.AccountSecurityEntity;
import dev.christopherbell.thevoid.invite.model.InviteCode;
import dev.christopherbell.thevoid.account.model.dto.Account;
import dev.christopherbell.thevoid.cry.model.Cry;
import dev.christopherbell.thevoid.account.model.dto.AccountDetails;
import dev.christopherbell.thevoid.account.model.dto.AccountSecurity;
import dev.christopherbell.thevoid.account.model.dto.VoidRolesEnum;
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
