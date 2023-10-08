package kbh.foerdervereinkita.mapper;

import java.util.Collection;
import java.util.stream.Collectors;
import kbh.foerdervereinkita.auth.UserRole;
import kbh.foerdervereinkita.storage.model.RoleEntity;
import kbh.foerdervereinkita.storage.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = RoleRepository.class)
public abstract class RoleMapper {

  @Autowired private RoleRepository repository;

  @Named("roleEnumsToRoleEntities")
  public Collection<RoleEntity> toRoleEntities(Collection<UserRole> userRoles) {
    return userRoles.stream()
        .map(role -> repository.findByRole(role).orElseThrow(IllegalStateException::new))
        .collect(Collectors.toList());
  }
}
