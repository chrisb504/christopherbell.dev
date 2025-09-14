package dev.christopherbell.account;

import static dev.christopherbell.libs.api.APIVersion.V20241215;
import static dev.christopherbell.libs.api.APIVersion.V20250903;

import dev.christopherbell.account.model.dto.AccountDetail;
import dev.christopherbell.account.model.dto.AccountCreateRequest;
import dev.christopherbell.account.model.AccountLoginRequest;
import dev.christopherbell.libs.api.model.Response;
import dev.christopherbell.permission.PermissionService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents the controller responsible for handling account related endpoints.
 */
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountController {
  private AccountService accountService;
  private PermissionService permissionService;

  @PostMapping(
      value = V20250903 + "/approve/{accountId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<AccountDetail>> approveAccount(
      @PathVariable String accountId
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.approveAccount(accountId))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Creates a new account.
   *
   * @param accountCreateRequest - the account create request.
   * @return the created account.
   * @throws Exception if there is an error creating the account.
   */
  @PostMapping(
      value = V20241215 + "/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Response<AccountDetail>> createAccount(
      @RequestBody AccountCreateRequest accountCreateRequest
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.createAccount(accountCreateRequest))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Deletes an account by its ID.
   *
   * @param accountId - the ID of the account to delete.
   * @return the deleted account.
   * @throws Exception if there is an error deleting the account.
   */
  @DeleteMapping(
      value = V20250903 + "/{accountId}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<AccountDetail>> deleteAccount(
      @PathVariable String accountId
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.deleteAccount(accountId))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Gets an account by its email.
   *
   * @param email - the ID of the account to get.
   * @return the account with the given ID.
   * @throws Exception if there is an error getting the account.
   */
  @GetMapping(
      value = V20241215 + "/email/{email}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<AccountDetail>> getAccountByEmail(
      @PathVariable String email
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.getAccountByEmail(email))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Gets an account by its ID.
   *
   * @param id - the ID of the account to get.
   * @return the account with the given ID.
   * @throws Exception if there is an error getting the account.
   */
  @GetMapping(
      value = V20250903 + "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<AccountDetail>> getAccountById(
      @PathVariable String id
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.getAccountById(id))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Gets an account by its username.
   *
   * @param username - the username of the account to get.
   * @return the account with the given username.
   * @throws Exception if there is an error getting the account.
   */
  @GetMapping(
      value = V20250903 + "/username/{username}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<AccountDetail>> getAccountByUsername(
      @PathVariable String username
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.getAccountByUsername(username))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Gets all accounts.
   *
   * @return a list of all accounts.
   */
  @GetMapping(
      value = V20241215,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<List<AccountDetail>>> getAccounts() {
    return new ResponseEntity<>(
        Response.<List<AccountDetail>>builder()
            .payload(accountService.getAccounts())
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Gets the account of the currently authenticated user.
   *
   * @return the account of the currently authenticated user.
   * @throws Exception if there is an error getting the account.
   */
  @GetMapping(
      value = V20250903 + "/me",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<AccountDetail>> getMyAccount(
  ) throws Exception {
    return new ResponseEntity<>(
        Response.<AccountDetail>builder()
            .payload(accountService.getSelfAccount())
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Logs in an account.
   *
   * @param accountLoginRequest - the account login request.
   * @return a JWT token if the login is successful.
   * @throws Exception if there is an error logging in the account.
   */
  @PostMapping(
      value = V20241215 + "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Response<String>> loginAccount(
      @RequestBody AccountLoginRequest accountLoginRequest
  ) throws Exception {
    return new ResponseEntity<>(Response.<String>builder()
        .payload(accountService.loginAccount(accountLoginRequest))
        .success(true)
        .build(), HttpStatus.OK);
  }
}
