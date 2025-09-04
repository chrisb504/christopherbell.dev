package dev.christopherbell.account;

import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.dto.AccountDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between Account entity and AccountDetail DTO.
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

  @Mapping(target = "inviteCode", ignore = true)
  @Mapping(target = "inviteCodeOwner", ignore = true)
  @Mapping(target = "loginToken", ignore = true)
  @Mapping(target = "passwordSalt", ignore = true)
  @Mapping(target = "passwordHash", ignore = true)
  Account toAccountEntity(AccountDetail accountDetail);

  AccountDetail toAccount(Account account);
}
