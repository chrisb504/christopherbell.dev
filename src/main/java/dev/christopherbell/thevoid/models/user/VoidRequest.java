package dev.christopherbell.thevoid.models.user;

import dev.christopherbell.libs.common.api.models.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.christopherbell.thevoid.models.domain.Cry;
import dev.christopherbell.thevoid.models.domain.account.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class VoidRequest extends Request {

  @JsonProperty("account")
  private Account account;
  @JsonProperty("cry")
  private Cry cry;
}
