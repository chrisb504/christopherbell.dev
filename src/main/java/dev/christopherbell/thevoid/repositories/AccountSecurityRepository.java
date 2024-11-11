package dev.christopherbell.thevoid.repositories;

import dev.christopherbell.thevoid.models.db.account.AccountSecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountSecurityRepository extends JpaRepository<AccountSecurityEntity, Long> {

  Optional<AccountSecurityEntity> findByEmail(String email);
}
