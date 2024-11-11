package dev.christopherbell.thevoid.controllers;

import dev.christopherbell.libs.common.api.models.Response;
import dev.christopherbell.libs.common.api.exceptions.NotFoundException;
import dev.christopherbell.libs.common.api.exceptions.InvalidRequestException;
import dev.christopherbell.libs.common.api.exceptions.InvalidTokenException;
import dev.christopherbell.thevoid.models.user.VoidRequest;
import dev.christopherbell.thevoid.models.user.VoidResponse;
import dev.christopherbell.thevoid.services.InviteCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/invite/code")
@RequiredArgsConstructor
@RestController
public class InviteCodeController {

  public final InviteCodeService inviteCodeService;

  @PostMapping(value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<VoidResponse>> generateInviteCode(@RequestHeader String clientId,
      @RequestHeader String loginToken,
      @RequestBody VoidRequest voidRequest)
      throws InvalidRequestException, NotFoundException, InvalidTokenException {
    return new ResponseEntity<>(Response.<VoidResponse>builder()
        .payload(inviteCodeService.generateInviteCode(clientId, loginToken, voidRequest))
        .success(true)
        .build(), HttpStatus.CREATED);
  }
}
