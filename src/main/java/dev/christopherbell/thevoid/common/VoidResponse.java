package dev.christopherbell.thevoid.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.christopherbell.thevoid.account.model.dto.AccountResponse;
import dev.christopherbell.thevoid.cry.model.Cry;
import dev.christopherbell.thevoid.invite.model.InviteCode;
import dev.christopherbell.thevoid.account.model.dto.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoidResponse {

  private List<Account> accounts;
  private List<Cry> cries;
  private HttpHeaders httpHeaders;
  private Account myself;
  private InviteCode inviteCode;
  private AccountResponse accountResponse;
}
