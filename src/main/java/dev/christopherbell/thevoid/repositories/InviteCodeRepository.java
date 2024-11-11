package dev.christopherbell.thevoid.repositories;

import dev.christopherbell.thevoid.models.db.InviteCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteCodeRepository extends JpaRepository<InviteCodeEntity, Long> {

}
