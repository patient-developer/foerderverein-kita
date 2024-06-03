package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import kbh.foerdervereinkita.security.Authority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(UserAuthorityId.class)
@Table(name = "user_authorities")
public class UserAuthorityEntity {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Id
  @Column(name = "authority")
  @Enumerated(EnumType.STRING)
  private Authority authority;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private UserEntity user;
}
