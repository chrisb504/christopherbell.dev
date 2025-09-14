package dev.christopherbell.account;

import com.mongodb.MongoWriteException;
import dev.christopherbell.account.model.dto.AccountDetail;
import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.AccountStatus;
import dev.christopherbell.account.model.dto.AccountCreateRequest;
import dev.christopherbell.account.model.AccountLoginRequest;
import dev.christopherbell.account.model.Role;
import dev.christopherbell.libs.api.exception.InvalidTokenException;
import dev.christopherbell.libs.api.exception.ResourceExistsException;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.libs.security.EmailSanitizer;
import dev.christopherbell.libs.security.PasswordUtil;
import dev.christopherbell.permission.PermissionService;
import dev.christopherbell.libs.security.UsernameSanitizer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Represents the service responsible for handling getting, creating, updating, and deleting
 * accounts.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountService {
  private final AccountMapper accountMapper;
  private final AccountRepository accountRepository;

  /**
   * Approves an account by setting its approvedBy field to the current user's ID and changing its
   * status to ACTIVE.
   *
   * @param accountId - the ID of the account to approve.
   * @return the approved account.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public AccountDetail approveAccount(String accountId) throws ResourceNotFoundException {
    log.info("Approving account with id {}", accountId);
    var account =
        accountRepository
            .findById(accountId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Account with id %s not found.", accountId)));
    var selfAccount = getSelfAccount();
    account.setApprovedBy(selfAccount.getId());
    account.setIsApproved(true);
    account.setStatus(AccountStatus.ACTIVE);
    account.setLastUpdatedOn(Instant.now());
    accountRepository.save(account);
    return accountMapper.toAccount(account);
  }

  /**
   * Creates a new account.
   *
   * @param accountCreateRequest - contains new information for an account.
   * @return back an account object if creation was successful.
   */
  public AccountDetail createAccount(AccountCreateRequest accountCreateRequest)
      throws ResourceExistsException {
    log.info("Creating account for username {}", accountCreateRequest.username());
    var account = createAccountEntity(accountCreateRequest);
    try {
      var salt = PasswordUtil.generateSalt();
      var hash = PasswordUtil.hashPassword(accountCreateRequest.password(), salt);
      account.setPasswordSalt(salt);
      account.setPasswordHash(hash);
      accountRepository.save(account);
      log.info("Successfully created account for username {}", accountCreateRequest.username());
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("Can't create account due to password issues", e);
    } catch (DuplicateKeyException | MongoWriteException e) {
      throw new ResourceExistsException("Account with given email or username already exists.", e);
    }
    return accountMapper.toAccount(account);
  }

  /**
   * Creates a new default account entity using a given account object.
   *
   * @param accountCreateRequest - the account to create the accountEntity based on.
   * @return a new account entity with default settings.
   */
  public Account createAccountEntity(AccountCreateRequest accountCreateRequest) {
    return Account.builder()
        .id(String.valueOf(UUID.randomUUID()))
        .approvedBy(null)
        .createdOn(Instant.now())
        .email(accountCreateRequest.email())
        .firstName(accountCreateRequest.firstName())
        .isApproved(false)
        .lastName(accountCreateRequest.lastName())
        .lastUpdatedOn(Instant.now())
        .role(Role.USER)
        .status(AccountStatus.INACTIVE)
        .username(accountCreateRequest.username())
        .build();
  }

  /**
   * Deletes an account by its ID.
   *
   * @param accountId the ID of the account to delete.
   * @return the deleted account.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public AccountDetail deleteAccount(String accountId) throws ResourceNotFoundException {
    log.info("Deleting account with id: {}", accountId);
    var account =
        accountRepository
            .findById(accountId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Account with id %s not found.", accountId)));
    accountRepository.delete(account);
    log.info("Successfully deleted account with id: {}", accountId);
    return accountMapper.toAccount(account);
  }

  /**
   * Gets an account by its email address.
   *
   * @param email the email address of the account.
   * @return the account with the given email address.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public AccountDetail getAccountByEmail(String email) throws ResourceNotFoundException {
    var sanitizedEmail = EmailSanitizer.sanitize(email);
    var account =
        accountRepository
            .findByEmail(sanitizedEmail)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Account with email %s not found.", sanitizedEmail)));
    return accountMapper.toAccount(account);
  }

  /**
   * Gets an account by its ID.
   *
   * @param id the ID of the account.
   * @return the account with the given ID.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public AccountDetail getAccountById(String id) throws ResourceNotFoundException {
    log.info("Getting account with id {}", id);
    var account =
        accountRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Account with id %s not found.", id)));
    log.info("Successfully got account with id {}", id);
    return accountMapper.toAccount(account);
  }

  /**
   * Gets an account by its username.
   *
   * @param username the username of the account.
   * @return the account with the given username.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public AccountDetail getAccountByUsername(String username) throws ResourceNotFoundException {
    var sanitizedUsername = UsernameSanitizer.sanitize(username);
    log.info("Getting account with username: {}", sanitizedUsername);
    var account =
        accountRepository
            .findByUsername(sanitizedUsername)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Account with username %s not found.", sanitizedUsername)));
    log.info("Successfully got account with username: {}", sanitizedUsername);
    return accountMapper.toAccount(account);
  }

  /**
   * List all accounts in the system.
   *
   * @return a list of all accounts.
   */
  public List<AccountDetail> getAccounts() {
    log.info("Getting all accounts");
    var accounts = accountRepository.findAll();
    return accounts.stream().map(accountMapper::toAccount).toList();
  }

  /**
   * Gets the account of the currently authenticated user.
   *
   * @return the account of the currently authenticated user.
   * @throws ResourceNotFoundException if the account cannot be found.
   */
  public AccountDetail getSelfAccount() throws ResourceNotFoundException {
    var selfId = PermissionService.getSelf();
    var account =
        accountRepository
            .findById(selfId)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        String.format("Account with id %s not found.", selfId)));
    return accountMapper.toAccount(account);
  }

  /**
   * Validates login information from a request and returns a JWT if it is correct.
   *
   * @param accountLoginRequest - account for which the requester wishes to gain access to.
   * @return a JWT token.
   * @throws InvalidTokenException - if login information is incorrect.
   */
  public String loginAccount(AccountLoginRequest accountLoginRequest) throws Exception {
    try {
      var email = accountLoginRequest.email();
      var sanitizedEmail = EmailSanitizer.sanitize(email);
      var account =
          accountRepository
              .findByEmail(sanitizedEmail)
              .orElseThrow(
                  () ->
                      new ResourceNotFoundException(
                          String.format("Account with email %s not found.", sanitizedEmail)));

      var isAuthenticated = PermissionService.isAuthenticated(accountLoginRequest, account);

      if (isAuthenticated) {
        if (PermissionService.isAccountActive(account.getStatus())) {
          account.setLastLoginOn(Instant.now());
          accountRepository.save(account);
          log.info("Successful login for account with id: {}", account.getId());
          return PermissionService.generateToken(account);
        } else {
          throw new AccountNotActiveException("Account is not active.");
        }
      } else {
        throw new InvalidTokenException("Given Login information was not correct.");
      }
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new InvalidTokenException("Error validating password: " + e.getMessage(), e);
    }
  }
}
