package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import kbh.foerdervereinkita.auth.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "roles",
    indexes = {@Index(name = "uc_roles_role", columnList = "role", unique = true)})
public class RoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private UserRole role;

  @ManyToMany
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<UserEntity> users = new LinkedHashSet<>();
}
