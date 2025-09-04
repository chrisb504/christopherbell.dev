package dev.christopherbell.account;

import com.azure.data.tables.models.TableServiceException;
import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.AccountEntity;
import dev.christopherbell.account.model.LoginRequest;
import dev.christopherbell.account.model.Role;
import dev.christopherbell.libs.common.api.exception.InvalidRequestException;
import dev.christopherbell.libs.common.api.exception.InvalidTokenException;
import dev.christopherbell.libs.common.api.exception.ResourceNotFoundException;
import dev.christopherbell.libs.common.security.EmailSanitizer;
import dev.christopherbell.libs.common.security.PasswordUtils;
import dev.christopherbell.libs.common.security.PermissionService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Represents the service responsible for handling getting, creating, updating, and deleting accounts.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountService {
  private final AccountMapper accountMapper;
  private final AccountRepository accountRepository;

  /**
   * Creates a new account.
   *
   * @param account - contains new information for an account.
   * @return back an account object if creation was successful.
   * @throws InvalidRequestException if something went wrong with the request.
   */
  public Account createAccount(Account account) throws InvalidRequestException {
    try {
      var accountEntity = createNewAccountEntity(account);
      PasswordUtils.saltPassword(account, accountEntity);
      accountRepository.save(accountEntity);
      return accountMapper.toAccount(accountEntity);
    } catch (TableServiceException e) {
      var statusCode = e.getResponse().getStatusCode();
      if (HttpStatus.BAD_REQUEST.value() == statusCode) {
        throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
      } else if(HttpStatus.ALREADY_REPORTED.value() == statusCode) {
        throw new RuntimeException("Failed to create account: " + e.getMessage(), e);
      }
      throw new InvalidRequestException("Failed to create account: " + e.getMessage(), e);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new InvalidRequestException("Failed to create account due to password salting: " + e.getMessage(), e);
    }
  }

  /**
   * Creates a new default account entity using a given account object.
   *
   * @param account - the account to create the accountEntity based on.
   * @return a new account entity with default settings.
   */
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

  /**
   * Gets an account by its email address.
   *
   * @param email the email address of the account.
   * @return the account with the given email address.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public Account getAccountByEmail(String email) throws ResourceNotFoundException {
    var sanitizedEmail = EmailSanitizer.sanitize(email);
    var accountEntity = accountRepository.findByEmail(sanitizedEmail)
        .orElseThrow(() ->
            new ResourceNotFoundException(
                String.format("Account with email %s not found.", sanitizedEmail)
            )
        );
      return accountMapper.toAccount(accountEntity);
  }

  /**
   * List all accounts on in the system.
   *
   * @return a list of all accounts.
   */
  public List<Account> getAccounts() {
    var accountEntities = accountRepository.findAll();
    return accountEntities.stream()
        .map(accountMapper::toAccount)
        .toList();
  }

  /**
   * Validates login information from a request and returns a JWT if it is correct.
   *
   * @param loginRequest - account for which the requester wishes to gain access to.
   * @return a JWT token.
   * @throws InvalidTokenException - if login information is incorrect.
   */
  public String loginAccount(LoginRequest loginRequest) throws InvalidTokenException {

    try {
      var email = loginRequest.email();
      var password = loginRequest.password();
      var sanitizedEmail = EmailSanitizer.sanitize(email);

      var accountEntity = accountRepository.findByEmail(sanitizedEmail)
          .orElseThrow(() ->
              new ResourceNotFoundException(
                  String.format("Account with email %s not found.", sanitizedEmail)
              )
          );

      var salt = accountEntity.getPasswordSalt();
      var hash = accountEntity.getPasswordHash();
      var isValidPassword = PasswordUtils.verifyPassword(password, salt, hash);

      if(isValidPassword) {
        PermissionService.isAccountApproved(accountEntity);
        return PermissionService.generateToken(accountEntity);
      } else {
        throw new InvalidTokenException("Given Login information was not correct.");
      }
    } catch (InvalidKeySpecException | NoSuchAlgorithmException | ResourceNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

}
