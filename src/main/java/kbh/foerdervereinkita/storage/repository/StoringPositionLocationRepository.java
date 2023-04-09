package kbh.foerdervereinkita.storage.repository;

import kbh.foerdervereinkita.storage.model.StoringPositionLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoringPositionLocationRepository
    extends JpaRepository<StoringPositionLocationEntity, Long> {}
