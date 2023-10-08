package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "users",
    indexes = {@Index(name = "uc_users_username", columnList = "username", unique = true)})
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @ManyToMany
  @JoinTable(
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Collection<RoleEntity> roles = new HashSet<>();
}
