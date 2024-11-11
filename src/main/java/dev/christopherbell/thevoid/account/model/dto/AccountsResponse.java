package dev.christopherbell.thevoid.account.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.christopherbell.thevoid.common.VoidResponse;
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
