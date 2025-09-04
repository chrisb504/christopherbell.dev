package dev.christopherbell.account;

import dev.christopherbell.account.model.AccountEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountEntity, UUID> {

  /**
   * Retrieves an {@link AccountEntity} by its unique email address.
   *
   * <p>
   * This query method leverages Spring Data's derived query generation.
   * If an {@code AccountEntity} with the given email exists, it will be
   * returned wrapped in an {@link Optional}; otherwise, the result will
   * be {@link Optional#empty()}.
   * </p>
   *
   * @param email the email address to look up (must not be {@code null})
   * @return an {@link Optional} containing the matching {@link AccountEntity}
   *         if found, or {@link Optional#empty()} if no match exists
   */
  Optional<AccountEntity> findByEmail(String email);

  /**
   * Finds an {@link AccountEntity} by its unique username.
   *
   * <p>
   * This query method is automatically implemented by Spring Data based
   * on the method name. If a record with the specified username exists,
   * it will be returned wrapped in an {@link Optional}; otherwise, the
   * result will be {@link Optional#empty()}.
   * </p>
   *
   * @param username the username to search for (must not be {@code null})
   * @return an {@link Optional} containing the matching {@link AccountEntity}
   *         if found, or {@link Optional#empty()} if no match exists
   */
  Optional<AccountEntity> findByUsername(String username);
}
