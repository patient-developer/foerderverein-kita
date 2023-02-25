package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.auth.UserRole;
import kbh.foerdervereinkita.service.UserRoleService;
import kbh.foerdervereinkita.storage.model.RoleEntity;
import kbh.foerdervereinkita.storage.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserRoleServiceImpl implements UserRoleService {

  private final RoleRepository repository;

  @Override
  public void initUserRoles() {

    for (UserRole userRole : UserRole.values()) {

      if (repository.findByRole(userRole).isEmpty()) {
        RoleEntity entity = new RoleEntity();
        entity.setRole(userRole);
        repository.save(entity);
      }
    }
  }
}
