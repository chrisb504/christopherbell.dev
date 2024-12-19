package dev.christopherbell.account;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.models.ListEntitiesOptions;
import com.azure.data.tables.models.TableEntity;
import com.azure.data.tables.models.TableServiceException;
import dev.christopherbell.account.models.Account;
import dev.christopherbell.account.models.AccountEntity;
import dev.christopherbell.configuration.ApiUtilProperties;
import dev.christopherbell.account.models.Role;
import dev.christopherbell.libs.common.api.exceptions.InvalidTokenException;
import dev.christopherbell.libs.common.api.exceptions.ResourceNotFoundException;
import dev.christopherbell.libs.common.api.utils.PasswordUtils;
import dev.christopherbell.permission.PermissionService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Represents the service responsible for handling getting, creating, updating, and deleting accounts.
 */
@AllArgsConstructor
@Slf4j
@Service
public class AccountService {

  private final ApiUtilProperties apiUtilProperties;
  private final TableClient tableClient;

  /**
   * Takes in an AccountEntity and returns back a TableEntity with all the properties from the AccountEntity.
   *
   * @param accountEntity - the AccountEntity to map to a TableEntity.
   * @return a TableEntity mapped from the given AccountEntity.
   */
  public TableEntity buildTableEntityFromAccountEntity(AccountEntity accountEntity) {
    return new TableEntity(
        AccountEntity.PARTITION_KEY,
        accountEntity.getEmail())
        .addProperty(AccountEntity.PROPERTY_APPROVED_BY, accountEntity.getApprovedBy())
        .addProperty(AccountEntity.PROPERTY_CREATED_ON, accountEntity.getCreatedOn())
        .addProperty(AccountEntity.PROPERTY_EMAIL, accountEntity.getEmail())
        .addProperty(AccountEntity.PROPERTY_FIRST_NAME, accountEntity.getFirstName())
        .addProperty(AccountEntity.PROPERTY_IS_APPROVED, accountEntity.getIsApproved())
        .addProperty(AccountEntity.PROPERTY_LAST_NAME, accountEntity.getLastName())
        .addProperty(AccountEntity.PROPERTY_PASSWORD_HASH, accountEntity.getPasswordHash())
        .addProperty(AccountEntity.PROPERTY_PASSWORD_SALT, accountEntity.getPasswordSalt())
        .addProperty(AccountEntity.PROPERTY_ROLE, accountEntity.getRole())
        .addProperty(AccountEntity.PROPERTY_USERNAME, accountEntity.getUsername());
  }

  public void createAccount(Account account) {
    try {
      var accountEntity = createNewAccountEntity(account);
      saltPassword(account, accountEntity);
      var entity = buildTableEntityFromAccountEntity(accountEntity);
      tableClient.createEntity(entity);
    } catch (TableServiceException e) {
      var statusCode = e.getResponse().getStatusCode();
      if (HttpStatus.BAD_REQUEST.value() == statusCode) {
        throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
      } else if(HttpStatus.ALREADY_REPORTED.value() == statusCode) {
        throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
      }
      throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create account due to password salting: " + e.getMessage(), e);
    }
  }

  public AccountEntity createNewAccountEntity(Account account) {
    return AccountEntity.builder()
        .approvedBy(null)
        .createdOn(Instant.now())
        .email(account.getEmail())
        .firstName(account.getFirstName())
        .isApproved(false)
        .lastName(account.getLastName())
        .role(Role.USER)
        .username(account.getUsername())
        .build();
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

  public String loginAccount(Account account) throws InvalidTokenException {

    try {
      var email = account.getEmail();
      var password = account.getPassword();

      var options = new ListEntitiesOptions().setFilter("email eq '" + email + "'");
      var tableEntityAccounts = tableClient.listEntities(options, null, null);
      var accountEntities = tableEntityAccounts.stream().map(this::mapEntityToAccount).toList();
      var accountEntity = accountEntities.getFirst();
      var salt = accountEntity.getPasswordSalt();
      var hash = accountEntity.getPasswordHash();
      var isValidPassword = PasswordUtils.verifyPassword(password, salt, hash, apiUtilProperties.getSaltPassword());

      if(isValidPassword) {
        return PermissionService.generateToken(accountEntity);
      } else {
        throw new InvalidTokenException("Given Login information was not correct.");
      }
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private AccountEntity mapEntityToAccount(TableEntity entity) {

    var approvedBy = (UUID) entity.getProperty(AccountEntity.PROPERTY_APPROVED_BY);
    var createdOn = (OffsetDateTime) entity.getProperty(AccountEntity.PROPERTY_CREATED_ON);
    var email = (String) entity.getProperty(AccountEntity.PROPERTY_EMAIL);
    var firstName = (String) entity.getProperty(AccountEntity.PROPERTY_FIRST_NAME);
    var isApproved = (Boolean) entity.getProperty(AccountEntity.PROPERTY_IS_APPROVED);
    var lastName = (String) entity.getProperty(AccountEntity.PROPERTY_LAST_NAME);
    var passwordHash = (String) entity.getProperty(AccountEntity.PROPERTY_PASSWORD_HASH);
    var passwordSalt =  (String) entity.getProperty(AccountEntity.PROPERTY_PASSWORD_SALT);
    var role = Role.valueOf((String) entity.getProperty(AccountEntity.PROPERTY_ROLE));
    var username = (String) entity.getProperty(AccountEntity.PROPERTY_USERNAME);

    return AccountEntity.builder()
        .approvedBy(approvedBy)
        .createdOn(createdOn.toInstant())
        .email(email)
        .firstName(firstName)
        .isApproved(isApproved)
        .lastName(lastName)
        .role(role)
        .rowKey(email)
        .passwordHash(passwordHash)
        .passwordSalt(passwordSalt)
        .username(username)
        .build();
  }

  public void saltPassword(Account account, AccountEntity accountEntity) throws Exception {
    var password = account.getPassword();
    var salt = PasswordUtils.generateSalt();
    var hash = PasswordUtils.hashPassword(password, salt, apiUtilProperties.getSaltPassword());
    accountEntity.setPasswordSalt(salt);
    accountEntity.setPasswordHash(hash);
  }
}
