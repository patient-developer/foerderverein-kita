package kbh.foerdervereinkita.storage.repository;

import kbh.foerdervereinkita.storage.model.MediaFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepository extends JpaRepository<MediaFileEntity, Long> {

  MediaFileEntity findById(long id);

  boolean existsByFileName(String fileName);
}
