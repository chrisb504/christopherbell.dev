package dev.christopherbell.thevoid.repositories;

import dev.christopherbell.thevoid.models.db.CryEntity;
import dev.christopherbell.thevoid.models.db.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryRepository extends JpaRepository<CryEntity, Long> {

  List<CryEntity> findByAccountEntity(AccountEntity accountEntity);
}