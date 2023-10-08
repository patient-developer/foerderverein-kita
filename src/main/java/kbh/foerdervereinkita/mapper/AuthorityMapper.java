package kbh.foerdervereinkita.mapper;

import java.util.Collection;
import java.util.stream.Collectors;
import kbh.foerdervereinkita.storage.model.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Mapper(componentModel = "spring")
public abstract class AuthorityMapper {

  @Named("roleEnumsToAuthorities")
  public Collection<? extends GrantedAuthority> toGrantedAuthorities(Collection<RoleEntity> roles) {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
        .collect(Collectors.toList());
  }
}
