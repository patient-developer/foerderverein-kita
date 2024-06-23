package kbh.foerdervereinkita.storage.repository;

import java.util.Collection;
import kbh.foerdervereinkita.storage.model.MediaFileEntity;
import kbh.foerdervereinkita.storage.model.MediaFileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MediaFileRepository extends JpaRepository<MediaFileEntity, Long> {
  Collection<MediaFileProjection> findAllProjectedBy();

  MediaFileEntity findById(long id);

  boolean existsByFileName(String fileName);

  @Modifying
  @Query("update MediaFileEntity m set m.description = :description where m.id = :id")
  void updateDescription(@Param("id") long id, @Param("description") String description);
}
