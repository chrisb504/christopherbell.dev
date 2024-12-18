package dev.christopherbell.account;

import dev.christopherbell.account.models.AccountEntity;
import dev.christopherbell.libs.common.api.exceptions.InvalidRequestException;
import dev.christopherbell.libs.common.api.exceptions.InvalidTokenException;
import dev.christopherbell.libs.common.api.exceptions.ResourceNotFoundException;
import dev.christopherbell.libs.common.api.models.Response;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class NewAccountController {
  private NewAccountService accountService;

  @PostMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> createAccount(@RequestBody AccountEntity accountEntity) {
    accountService.createAccount(accountEntity);
    return new ResponseEntity<>(
        Response.builder()
            .success(true)
            .build(), HttpStatus.OK);
  }

  @GetMapping(value = "/v1/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<AccountEntity>> getAccountById(@RequestHeader String clientId,
      @PathVariable String accountId) throws InvalidRequestException, ResourceNotFoundException {
    return new ResponseEntity<>(
        Response.<AccountEntity>builder()
            .payload(accountService.getAccount(clientId, accountId))
            .success(true)
            .build(), HttpStatus.OK);
  }

  @GetMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<List<AccountEntity>>> getAccounts() throws InvalidRequestException, ResourceNotFoundException {
    return new ResponseEntity<>(
        Response.<List<AccountEntity>>builder()
            .payload(accountService.getAllAccounts())
            .success(true)
            .build(), HttpStatus.OK);
  }

  @PostMapping(value = "/v1/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<String>> loginAccount(@RequestBody AccountEntity accountEntity)
      throws InvalidRequestException, ResourceNotFoundException, InvalidTokenException {

    return new ResponseEntity<>(Response.<String>builder()
        .payload(accountService.loginAccount(accountEntity))
        .success(true)
        .build(), HttpStatus.OK);
  }

}
