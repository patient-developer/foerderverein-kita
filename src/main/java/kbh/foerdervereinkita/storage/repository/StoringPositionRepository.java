package kbh.foerdervereinkita.storage.repository;

import java.util.Optional;
import kbh.foerdervereinkita.storage.model.StoringPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoringPositionRepository extends JpaRepository<StoringPositionEntity, Long> {

  Optional<StoringPositionEntity> findByEventRegistration_Email(String email);
}
