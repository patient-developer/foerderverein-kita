package kbh.foerdervereinkita.storage.repository;

import java.util.Collection;
import kbh.foerdervereinkita.storage.model.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

  Collection<PictureEntity> findAllByEventKey(String eventKey);
}
