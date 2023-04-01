package kbh.foerdervereinkita.storage.repository;

import kbh.foerdervereinkita.storage.model.StoringPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StoringPositionRepository extends JpaRepository<StoringPositionEntity, Long> {

  Optional<StoringPositionEntity> findByEventRegistration_Email(String email);
}
