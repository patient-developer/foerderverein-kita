package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.dto.EventRegistrationDto;
import kbh.foerdervereinkita.mapper.EventRegistrationMapper;
import kbh.foerdervereinkita.service.EventRegistrationService;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import kbh.foerdervereinkita.storage.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventRegistrationServiceImpl implements EventRegistrationService {
  private final EventRegistrationRepository repository;
  private final EventRegistrationMapper mapper;

  @Override
  public void register(EventRegistrationDto eventRegistrationDto) {

    EventRegistrationEntity entity = mapper.toEntity(eventRegistrationDto);

    repository.save(entity);
  }
}
