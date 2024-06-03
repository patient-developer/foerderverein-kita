package kbh.foerdervereinkita.storage.model;

import java.io.Serializable;
import kbh.foerdervereinkita.security.Authority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthorityId implements Serializable {

  private Long userId;
  private Authority authority;
}
