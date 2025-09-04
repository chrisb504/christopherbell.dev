package dev.christopherbell.account;

import static dev.christopherbell.libs.common.api.util.APIVersion.V20241215;

import dev.christopherbell.account.model.Account;
import dev.christopherbell.account.model.LoginRequest;
import dev.christopherbell.libs.common.api.exception.InvalidRequestException;
import dev.christopherbell.libs.common.api.exception.InvalidTokenException;
import dev.christopherbell.libs.common.api.exception.ResourceNotFoundException;
import dev.christopherbell.libs.common.api.model.Response;
import dev.christopherbell.libs.common.security.PermissionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountController {
  private AccountService accountService;
  private PermissionService permissionService;

  @PostMapping(value = V20241215 + "/create",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Account>> createAccount(@RequestBody Account account) throws InvalidRequestException {
    return new ResponseEntity<>(
        Response.<Account>builder()
            .payload(accountService.createAccount(account))
            .success(true)
            .build(), HttpStatus.OK);
  }

  @GetMapping(value = V20241215 + "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<Account>> getAccountById(@PathVariable String email) throws ResourceNotFoundException {
    return new ResponseEntity<>(
        Response.<Account>builder()
            .payload(accountService.getAccountByEmail(email))
            .success(true)
            .build(), HttpStatus.OK);
  }

  @GetMapping(value = V20241215, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<List<Account>>> getAccounts() {

    return new ResponseEntity<>(
        Response.<List<Account>>builder()
            .payload(accountService.getAccounts())
            .success(true)
            .build(), HttpStatus.OK);
  }

  @PostMapping(value = V20241215 + "/login",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<String>> loginAccount(@RequestBody LoginRequest loginRequest) throws InvalidTokenException {
    return new ResponseEntity<>(Response.<String>builder()
        .payload(accountService.loginAccount(loginRequest))
        .success(true)
        .build(), HttpStatus.OK);
  }
}
