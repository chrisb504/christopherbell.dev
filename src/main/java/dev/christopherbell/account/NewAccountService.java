package dev.christopherbell.account;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.models.ListEntitiesOptions;
import com.azure.data.tables.models.TableEntity;
import com.azure.data.tables.models.TableServiceException;
import dev.christopherbell.account.models.AccountEntity;
import dev.christopherbell.account.models.Role;
import dev.christopherbell.libs.common.api.exceptions.ResourceNotFoundException;
import dev.christopherbell.permission.PermissionService;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NewAccountService {
  private final TableClient tableClient;

  public TableEntity buildAccountTableEntity(AccountEntity accountEntity) {
    return new TableEntity(
        AccountEntity.PARTITION_KEY,
        accountEntity.getEmail())
        .addProperty(AccountEntity.PROPERTY_APPROVED_BY, accountEntity.getApprovedBy())
        .addProperty(AccountEntity.PROPERTY_CREATED_ON, Instant.now())
        .addProperty(AccountEntity.PROPERTY_EMAIL, accountEntity.getEmail())
        .addProperty(AccountEntity.PROPERTY_IS_APPROVED, false)
        .addProperty(AccountEntity.PROPERTY_NAME, accountEntity.getName())
        .addProperty(AccountEntity.PROPERTY_PASSWORD, accountEntity.getPassword())
        .addProperty(AccountEntity.PROPERTY_ROLE, Role.USER.name());
  }

  public void createAccount(AccountEntity accountEntity) {
    try {
      var entity = buildAccountTableEntity(accountEntity);
      tableClient.createEntity(entity);
    } catch (TableServiceException e) {
      var statusCode = e.getResponse().getStatusCode();
      if (HttpStatus.BAD_REQUEST.value() == statusCode) {
        throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
      } else if(HttpStatus.ALREADY_REPORTED.value() == statusCode) {
        throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
      }
      throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
    }
  }

  public AccountEntity getAccount(String partitionKey, String rowKey) throws ResourceNotFoundException {
    try {
      TableEntity entity = tableClient.getEntity(partitionKey, rowKey);
      return mapEntityToAccount(entity);
    } catch (TableServiceException e) {
      if (e.getResponse().getStatusCode() == 404) {
        throw new ResourceNotFoundException("can't find resource");
      }
      throw new RuntimeException("Failed to retrieve account: " + e.getMessage(), e);
    }
  }

  public List<AccountEntity> getAllAccounts() {
    var options = new ListEntitiesOptions().setFilter("PartitionKey eq 'ACCOUNT'");
    var accountEntities = tableClient.listEntities(options, null, null);
    return accountEntities.stream().map(this::mapEntityToAccount).toList();
  }

  public String loginAccount(AccountEntity accountEntity) {
    var email = accountEntity.getEmail();
    var password = accountEntity.getPassword();

    var options = new ListEntitiesOptions().setFilter("email eq '" + email + "'");
    var accountEntities = tableClient.listEntities(options, null, null);
    var accounts = accountEntities.stream().map(this::mapEntityToAccount).toList();

    var key = PermissionService.generateToken(email);

    return key;
  }

  private AccountEntity mapEntityToAccount(TableEntity entity) {

    var approvedBy = (UUID) entity.getProperty(AccountEntity.PROPERTY_APPROVED_BY);
    var createdOn = (OffsetDateTime) entity.getProperty(AccountEntity.PROPERTY_CREATED_ON);
    var email = (String) entity.getProperty(AccountEntity.PROPERTY_EMAIL);
    var isApproved = (Boolean) entity.getProperty(AccountEntity.PROPERTY_IS_APPROVED);
    var name = (String) entity.getProperty(AccountEntity.PROPERTY_NAME);
    var role = Role.valueOf((String) entity.getProperty(AccountEntity.PROPERTY_ROLE));

    return AccountEntity.builder()
        .approvedBy(approvedBy)
        .createdOn(createdOn.toInstant())
        .email(email)
        .isApproved(isApproved)
        .name(name)
        .role(role)
        .rowKey(email)
        .build();
  }
}
