package dev.christopherbell.thevoid.controllers;

import dev.christopherbell.libs.common.api.models.Response;
import dev.christopherbell.libs.common.api.exceptions.ResourceNotFoundException;
import dev.christopherbell.libs.common.api.exceptions.InvalidTokenException;
import dev.christopherbell.thevoid.models.user.VoidRequest;
import dev.christopherbell.thevoid.models.user.VoidResponse;
import dev.christopherbell.thevoid.services.CryService;
import lombok.RequiredArgsConstructor;
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

@RequestMapping("/api/cries")
@RequiredArgsConstructor
@RestController
public class CryController {

  private final CryService cryService;


  @PostMapping(value = "/v1/create/cry/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<VoidResponse>> createCry(@RequestHeader String clientId,
      @RequestHeader String loginToken,
      @PathVariable Long accountId,
      @RequestBody VoidRequest voidRequest) throws ResourceNotFoundException, InvalidTokenException {
    return new ResponseEntity<>(Response.<VoidResponse>builder()
        .payload(cryService.createCry(clientId, loginToken, accountId, voidRequest))
        .success(true)
        .build(), HttpStatus.CREATED);
  }

  @GetMapping(value = "/v1/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<VoidResponse>> getAllCriesByAccountId(@RequestHeader String clientId,
      @RequestHeader String loginToken,
      @PathVariable Long accountId) throws ResourceNotFoundException {
    return new ResponseEntity<>(Response.<VoidResponse>builder()
        .payload(cryService.getAllCriesByAccountId(clientId, accountId))
        .success(true)
        .build(), HttpStatus.OK);
  }

  // Get All Cries for a Given user

  // Get All Followers for a Given User

  // Get All Following for a Given User

  // Get All replies for a given Cry

  // Get Total Lifespan for a given cry

  // Get remaining lifespan for a given cry
}
