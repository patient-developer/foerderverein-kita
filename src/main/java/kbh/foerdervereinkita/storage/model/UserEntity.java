package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "is_enabled")
  private boolean enabled;

  @OneToMany(
          cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
          mappedBy = "user",
          orphanRemoval = true)
  private Set<UserAuthorityEntity> userAuthorities = new HashSet<>();

  public void addUserAuthority(UserAuthorityEntity userAuthority) {
    this.userAuthorities.add(userAuthority);
    userAuthority.setUser(this);
  }
}
