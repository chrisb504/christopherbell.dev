package dev.christopherbell.thevoid.services.messengers;

import dev.christopherbell.thevoid.models.db.CryEntity;
import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import dev.christopherbell.thevoid.repositories.CryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CryMessenger {

  private final CryRepository cryRepository;

  /**
   *
   * @param accountEntity
   * @return
   */
  public List<CryEntity> findByAccountEntity(AccountEntity accountEntity) {
    return this.cryRepository.findByAccountEntity(accountEntity);
  }

  /**
   *
   * @param cryEntity
   */
  public void saveCryRepository(CryEntity cryEntity) {
    this.cryRepository.save(cryEntity);
  }
}
