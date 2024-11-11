package dev.christopherbell.thevoid.repositories;

import dev.christopherbell.thevoid.models.db.account.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, Long> {

}
