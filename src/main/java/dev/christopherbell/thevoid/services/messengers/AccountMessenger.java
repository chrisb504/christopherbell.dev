package dev.christopherbell.thevoid.services.messengers;

import dev.christopherbell.libs.common.api.exceptions.ResourceNotFoundException;
import dev.christopherbell.thevoid.models.db.account.AccountDetailsEntity;
import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import dev.christopherbell.thevoid.models.db.account.AccountSecurityEntity;
import dev.christopherbell.thevoid.repositories.AccountDetailsRepository;
import dev.christopherbell.thevoid.repositories.AccountRepository;
import dev.christopherbell.thevoid.repositories.AccountSecurityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountMessenger {

  private final AccountRepository accountRepository;
  private final AccountDetailsRepository accountDetailsRepository;
  private final AccountSecurityRepository accountSecurityRepository;


  /**
   *
   * @return
   */
  public List<AccountEntity> getAccountEntities() {
    return this.accountRepository.findAll();
  }

  /**
   *
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public AccountEntity getAccountEntityById(Long id) throws ResourceNotFoundException {
    var maybeAccountEntity = this.accountRepository.findById(id);
    if (maybeAccountEntity.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return maybeAccountEntity.get();
  }

  /**
   *
   * @param username
   * @return
   * @throws ResourceNotFoundException
   */
  public AccountEntity getAccountEntityByUsername(String username) throws ResourceNotFoundException {
    var maybeAccountEntity = this.accountRepository.findByUsername(username);
    if (maybeAccountEntity.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return maybeAccountEntity.get();
  }

  /**
   *
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public AccountDetailsEntity getAccountDetailsEntityById(Long id) throws ResourceNotFoundException {
    var maybeAccountDetailsEntity = this.accountDetailsRepository.findById(id);
    if (maybeAccountDetailsEntity.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return maybeAccountDetailsEntity.get();
  }

  /**
   *
   * @param email
   * @return
   * @throws ResourceNotFoundException
   */
  public AccountSecurityEntity getAccountSecurityEntityByEmail(String email) throws ResourceNotFoundException {
    var maybeAccountSecurityEntity = this.accountSecurityRepository.findByEmail(email);
    if (maybeAccountSecurityEntity.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return maybeAccountSecurityEntity.get();
  }

  /**
   *
   * @param id
   * @return
   * @throws ResourceNotFoundException
   */
  public AccountSecurityEntity getAccountSecurityEntityById(Long id) throws ResourceNotFoundException {
    var maybeAccountSecurityEntity = this.accountSecurityRepository.findById(id);
    if (maybeAccountSecurityEntity.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return maybeAccountSecurityEntity.get();
  }

  /**
   *
   * @param accountEntity
   */
  public void saveAccountRepository(AccountEntity accountEntity) {
    this.accountRepository.save(accountEntity);
  }

  /**
   *
   * @param accountDetailsEntity
   */
  public void saveAccountDetailsRepository(AccountDetailsEntity accountDetailsEntity) {
    this.accountDetailsRepository.save(accountDetailsEntity);
  }

  /**
   *
   * @param accountSecurityEntity
   */
  public void saveAccountSecurityRepository(AccountSecurityEntity accountSecurityEntity) {
    this.accountSecurityRepository.save(accountSecurityEntity);
  }

  /**
   *
   * @param searchTerm
   * @return
   */
  public List<AccountEntity> searchAccounts(String searchTerm) {
    return this.accountRepository.findByUsernameContaining(searchTerm);
  }
}
