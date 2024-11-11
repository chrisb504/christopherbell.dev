package dev.christopherbell.thevoid.repositories;

import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

  Optional<AccountEntity> findByUsername(String username);
  List<AccountEntity> findByUsernameContaining(String searchTerm);
}
