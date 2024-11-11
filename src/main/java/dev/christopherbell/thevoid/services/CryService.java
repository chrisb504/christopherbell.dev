package dev.christopherbell.thevoid.services;

import dev.christopherbell.libs.common.api.exceptions.NotFoundException;
import dev.christopherbell.libs.common.api.exceptions.InvalidTokenException;
import dev.christopherbell.thevoid.models.user.VoidRequest;
import dev.christopherbell.thevoid.models.user.VoidResponse;
import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import dev.christopherbell.thevoid.models.domain.Cry;
import dev.christopherbell.thevoid.services.messengers.AccountMessenger;
import dev.christopherbell.thevoid.services.messengers.CryMessenger;
import dev.christopherbell.thevoid.utils.mappers.MapStructMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class CryService {

  private final AccountMessenger accountMessenger;
  private final CryMessenger cryMessenger;
  private final MapStructMapper mapStructMapper;
  private final PermissionsService permissionsService;

  /**
   *
   * @param loginToken
   * @param accountId
   * @param voidRequest
   * @return
   * @throws NotFoundException
   * @throws InvalidTokenException
   */
  public VoidResponse createCry(String clientId, String loginToken, Long accountId, VoidRequest voidRequest)
      throws NotFoundException, InvalidTokenException {
    log.info("Request for all cries for given accountId: " + accountId);

    // Validate the token for the user
    if (!this.permissionsService.validateLoginToken(loginToken, accountId)) {
      throw new NotFoundException();
    }
    var cry = Objects.requireNonNullElse(voidRequest.getCry(), new Cry());

    // Convert Cry to CryEntity
    var cryEntity = this.mapStructMapper.mapToCryEntity(cry);
    var accountEntity = this.accountMessenger.getAccountEntityById(accountId);
    cryEntity.setAccountEntity(accountEntity);
    this.cryMessenger.saveCryRepository(cryEntity);

    return VoidResponse.builder().build();
  }

  /**
   *
   * @param accountId
   * @return
   * @throws NotFoundException
   */
  public VoidResponse getAllCriesByAccountId(String clientId, Long accountId) throws NotFoundException {
    //TODO: Write a test to see if this will prevent a DB attack
    //var username = Jsoup.clean(dirtyUsername, Safelist.basic());
    log.info("Request for all cries for given accountId: " + accountId);

    var accountEntity = this.accountMessenger.getAccountEntityById(accountId);
    var cryEntities = this.cryMessenger.findByAccountEntity(accountEntity);
    var cries = new ArrayList<Cry>();
    for (var cryEntity : cryEntities) {
      var cryAuthorAccount = Objects.requireNonNullElse(cryEntity.getAccountEntity(), new AccountEntity());
      var authorUsername = Objects.requireNonNullElse(cryAuthorAccount.getUsername(), "Anon");
      var authorAccountId = Objects.requireNonNullElse(String.valueOf(cryAuthorAccount.getId()), "");
      var cry = this.mapStructMapper.mapToCry(cryEntity);
      cry.setAuthor(authorUsername);
      cry.setAuthorAccountId(authorAccountId);
      cries.add(cry);
    }

    return VoidResponse.builder()
        .cries(cries)
        .build();
  }

}
