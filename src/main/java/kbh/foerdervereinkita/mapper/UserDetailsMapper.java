package kbh.foerdervereinkita.mapper;

import kbh.foerdervereinkita.auth.UserDetailsImpl;
import kbh.foerdervereinkita.dto.UserDto;
import kbh.foerdervereinkita.storage.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {RoleMapper.class, AuthorityMapper.class, PasswordMapper.class})
public interface UserDetailsMapper {

  @Mapping(target = "authorities", source = "roles", qualifiedByName = "roleEnumsToAuthorities")
  @Mapping(target = "accountNonExpired", expression = "java(true)")
  @Mapping(target = "accountNonLocked", expression = "java(true)")
  @Mapping(target = "credentialsNonExpired", expression = "java(true)")
  @Mapping(target = "enabled", expression = "java(true)")
  UserDetailsImpl toUserDetailsImpl(UserEntity entity);

  @Mapping(target = "id", source = "userId", defaultValue = "0L")
  @Mapping(target = "password", source = "password", qualifiedByName = "toEncodedPassword")
  @Mapping(target = "roles", source = "roles", qualifiedByName = "roleEnumsToRoleEntities")
  UserEntity toUserEntity(UserDto user);
}
