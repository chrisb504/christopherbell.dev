package dev.christopherbell.thevoid.models.user.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.christopherbell.thevoid.models.user.VoidResponse;
import dev.christopherbell.thevoid.models.domain.account.Account;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class AccountsResponse extends VoidResponse implements Serializable {

  @JsonProperty("accounts")
  private List<Account> accounts;
}
