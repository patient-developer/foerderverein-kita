package kbh.foerdervereinkita.users.registration;

import java.util.Set;
import kbh.foerdervereinkita.security.Authority;
import kbh.foerdervereinkita.storage.model.UserAuthorityEntity;
import kbh.foerdervereinkita.storage.model.UserEntity;
import kbh.foerdervereinkita.users.UserDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = {Authority.class, Set.class, SimpleGrantedAuthority.class})
public abstract class UserRegistrationMapper {

  @Autowired protected PasswordEncoder encoder;

  @Mapping(target = "id", expression = "java(null)")
  @Mapping(target = "name", expression = "java(form.getName().strip())")
  @Mapping(target = "password", expression = "java(form.getPassword().strip())")
  @Mapping(target = "authority", source = "authority")
  @Mapping(target = "enabled", expression = "java(true)")
  abstract UserDTO toDTO(UserForm form);

  @Mapping(target = "id", expression = "java(null)")
  @Mapping(target = "name", expression = "java(dto.name().strip())")
  @Mapping(target = "password", expression = "java(encoder.encode(dto.password().strip()))")
  @Mapping(target = "userAuthorities", ignore = true)
  @Mapping(target = "enabled", source = "enabled")
  abstract UserEntity toEntity(UserDTO dto);

  @AfterMapping
  protected void setUserAuthorities(@MappingTarget UserEntity user, UserDTO dto) {

    var userAuthority = new UserAuthorityEntity();

    userAuthority.setUserId(user.getId());
    userAuthority.setAuthority(dto.authority());

    user.addUserAuthority(userAuthority);
  }
}
