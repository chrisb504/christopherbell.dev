package dev.christopherbell.thevoid.common;

import dev.christopherbell.libs.common.api.models.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.christopherbell.thevoid.cry.model.Cry;
import dev.christopherbell.thevoid.account.model.dto.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class VoidRequest extends Request {

  private Account account;
  private Cry cry;
}
