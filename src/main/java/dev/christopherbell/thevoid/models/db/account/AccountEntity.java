package dev.christopherbell.thevoid.models.db.account;

import dev.christopherbell.thevoid.models.db.CryEntity;
import dev.christopherbell.thevoid.models.db.InviteCodeEntity;
import dev.christopherbell.thevoid.models.db.VoidRoleEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "account", schema = "void_api")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column(unique = true)
  private String username;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private VoidRoleEntity voidRoleEntity;

  @OneToMany(targetEntity = CryEntity.class, mappedBy = "accountEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CryEntity> cries;

  @OneToOne(targetEntity = AccountDetailsEntity.class, mappedBy = "accountEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private AccountDetailsEntity accountDetailsEntity;

  @OneToOne(targetEntity = AccountSecurityEntity.class, mappedBy = "accountEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private AccountSecurityEntity accountSecurityEntity;

  @OneToMany(targetEntity = InviteCodeEntity.class, mappedBy = "accountEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<InviteCodeEntity> inviteCodeEntities;
}
