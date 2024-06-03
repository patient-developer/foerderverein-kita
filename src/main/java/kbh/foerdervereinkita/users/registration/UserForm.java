package kbh.foerdervereinkita.users.registration;

import kbh.foerdervereinkita.security.Authority;
import lombok.Data;

@Data
public class UserForm {

  private String name;
  private String password;
  private Authority authority;
}
