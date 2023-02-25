package kbh.foerdervereinkita.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring", uses = BCryptPasswordEncoder.class)
public class PasswordMapper {

  @Autowired private BCryptPasswordEncoder encoder;

  @Named("toEncodedPassword")
  public String toEncodedPassword(String password) {
    return encoder.encode(password.strip());
  }
}
