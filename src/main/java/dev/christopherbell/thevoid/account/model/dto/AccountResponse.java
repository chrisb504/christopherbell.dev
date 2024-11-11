package dev.christopherbell.thevoid.account.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.christopherbell.thevoid.common.VoidResponse;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@SuperBuilder
public class AccountResponse extends VoidResponse implements Serializable {

  private Account account;
}
