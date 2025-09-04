package dev.christopherbell.account;

import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  AccountEntity toAccountEntity(Account account);

  Account toAccount(AccountEntity accountEntity);
}
