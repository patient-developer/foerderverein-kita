package kbh.foerdervereinkita.storage.repository;

import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRegistrationRepository extends JpaRepository<EventRegistrationEntity, Long> {}
