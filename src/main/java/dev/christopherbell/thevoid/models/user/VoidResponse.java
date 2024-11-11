package dev.christopherbell.thevoid.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.christopherbell.thevoid.models.user.account.AccountResponse;
import dev.christopherbell.thevoid.models.domain.Cry;
import dev.christopherbell.thevoid.models.domain.InviteCode;
import dev.christopherbell.thevoid.models.domain.account.Account;
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
