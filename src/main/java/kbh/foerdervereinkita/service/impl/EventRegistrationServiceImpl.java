package kbh.foerdervereinkita.service.impl;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import kbh.foerdervereinkita.dto.EMailDto;
import kbh.foerdervereinkita.dto.EventRegistrationDto;
import kbh.foerdervereinkita.mapper.EMailMapper;
import kbh.foerdervereinkita.mapper.EventRegistrationMapper;
import kbh.foerdervereinkita.service.EMailService;
import kbh.foerdervereinkita.service.EventRegistrationService;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import kbh.foerdervereinkita.storage.repository.EventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EventRegistrationServiceImpl implements EventRegistrationService {

  private final EventRegistrationRepository repository;
  private final EventRegistrationMapper eventRegistrationMapper;
  private final EMailMapper eMailMapper;

  @Value("${MAX_REGISTRATION_COUNT}")
  private final long maxRegistrationsCount;

  private final EMailService eMailService;

  @Override
  public void register(EventRegistrationDto eventRegistration) {

    EventRegistrationEntity entity = eventRegistrationMapper.toEntity(eventRegistration);

    repository.save(entity);

    CompletableFuture.runAsync(
        () -> {
          try {
            EMailDto eMail = eMailMapper.toDto(eventRegistration);
            eMailService.sendMail(eMail);
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        });
  }

  @Override
  public boolean exists(String eMail) {
    return repository.existsByEmail(eMail);
  }

  @Override
  public Collection<EventRegistrationDto> fetchAll() {

    Collection<EventRegistrationEntity> entities = repository.findAll();

    return entities.stream().map(eventRegistrationMapper::toDto).toList();
  }

  @Override
  public long registrationVacanciesCount() {
    return maxRegistrationsCount - repository.count();
  }
}
