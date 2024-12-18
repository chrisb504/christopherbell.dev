package dev.christopherbell.account;

import com.azure.data.tables.TableClient;
import dev.christopherbell.account.models.AccountEntity;
import dev.christopherbell.account.models.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @InjectMocks
  private AccountService accountService;

  @Mock
  private TableClient tableClient;

  @Test
  public void buildTableEntityFromAccountEntity() {

    var accountEntity = AccountStub.getAccountEntityStub();
    var tableEntity = accountService.buildTableEntityFromAccountEntity(accountEntity);

    var tableEntityApprovedBy = tableEntity.getProperty(AccountEntity.PROPERTY_APPROVED_BY);
    var tableEntityCreatedOn = tableEntity.getProperty(AccountEntity.PROPERTY_CREATED_ON);
    var tableEntityEmail = tableEntity.getRowKey();
    var tableEntityFirstName = tableEntity.getProperty(AccountEntity.PROPERTY_FIRST_NAME);
    var tableEntityIsApproved = tableEntity.getProperty(AccountEntity.PROPERTY_IS_APPROVED);
    var tableEntityLastName = tableEntity.getProperty(AccountEntity.PROPERTY_LAST_NAME);
    var tableEntityRole = tableEntity.getProperty(AccountEntity.PROPERTY_ROLE);
    var tableEntityUsername = tableEntity.getProperty(AccountEntity.PROPERTY_USERNAME);

    Assertions.assertEquals(accountEntity.getApprovedBy(), tableEntityApprovedBy);
    Assertions.assertNotNull(tableEntityCreatedOn);
    Assertions.assertEquals(accountEntity.getEmail(), tableEntityEmail);
    Assertions.assertEquals(accountEntity.getFirstName(), tableEntityFirstName);
    Assertions.assertEquals(accountEntity.getIsApproved(), tableEntityIsApproved);
    Assertions.assertEquals(accountEntity.getLastName(), tableEntityLastName);
    Assertions.assertEquals(accountEntity.getRole(), tableEntityRole);
    Assertions.assertEquals(accountEntity.getUsername(), tableEntityUsername);
  }

  @Test
  public void createNewAccountEntity() {

    var account = AccountStub.getAccountStub();
    var accountEntity = accountService.createNewAccountEntity(account);

    Assertions.assertEquals(account.getApprovedBy(), accountEntity.getApprovedBy());
    Assertions.assertNotNull(accountEntity.getCreatedOn());
    Assertions.assertEquals(account.getEmail(), accountEntity.getEmail());
    Assertions.assertEquals(account.getFirstName(), accountEntity.getFirstName());
    Assertions.assertFalse(accountEntity.getIsApproved());
    Assertions.assertEquals(account.getLastName(), accountEntity.getLastName());
    Assertions.assertEquals(Role.USER, accountEntity.getRole());
    Assertions.assertEquals(account.getUsername(), accountEntity.getUsername());
  }

}
