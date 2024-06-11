package kbh.foerdervereinkita.storage.repository;

import java.util.Collection;
import kbh.foerdervereinkita.storage.model.MediaFileEntity;
import kbh.foerdervereinkita.storage.model.MediaFileProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepository extends JpaRepository<MediaFileEntity, Long> {
  Collection<MediaFileProjection> findAllProjectedBy();

  MediaFileEntity findById(long id);

  boolean existsByFileName(String fileName);
}
