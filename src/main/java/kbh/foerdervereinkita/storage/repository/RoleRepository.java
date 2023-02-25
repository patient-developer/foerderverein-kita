package kbh.foerdervereinkita.storage.repository;

import kbh.foerdervereinkita.auth.UserRole;
import kbh.foerdervereinkita.storage.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  Optional<RoleEntity> findByRole(UserRole userRole);
}
