package kbh.foerdervereinkita.storage.repository;

import java.util.Optional;
import kbh.foerdervereinkita.storage.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  boolean existsByUsername(String username);
}
