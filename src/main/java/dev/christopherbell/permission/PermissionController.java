package dev.christopherbell.permission;

import dev.christopherbell.libs.common.api.models.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/permission")
@RestController
public class PermissionController {

  private final PermissionService permissionService;

  @GetMapping(value = "/v1/getToken", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<String>> getToken() {
    return new ResponseEntity<>(
        Response.<String>builder()
            .payload(PermissionService.generateToken("me"))
            .success(true)
            .build(), HttpStatus.OK);
  }
}
