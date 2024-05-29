package kbh.foerdervereinkita.security;

import java.util.Collection;
import java.util.Set;
import kbh.foerdervereinkita.storage.model.UserAuthorityEntity;
import kbh.foerdervereinkita.storage.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

  @Mapping(target = "userId", source = "id")
  @Mapping(target = "username", source = "name")
  @Mapping(target = "password", source = "password")
  @Mapping(target = "authorities", source = "userAuthorities", qualifiedByName = "toAuthorities")
  @Mapping(target = "enabled", source = "enabled")
  UserDetailsImpl toImpl(UserEntity entity);

  @Named("toAuthorities")
  default Collection<? extends GrantedAuthority> toAuthorities(
          Set<UserAuthorityEntity> userAuthorities) {

    return userAuthorities.stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().name()))
            .toList();
  }
}
