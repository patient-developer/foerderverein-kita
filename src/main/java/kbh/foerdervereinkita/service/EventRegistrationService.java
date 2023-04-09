package kbh.foerdervereinkita.service;

import java.util.Collection;
import kbh.foerdervereinkita.dto.EventRegistrationDto;

public interface EventRegistrationService {

  void register(EventRegistrationDto eventRegistration);

  boolean exists(String eMail);

  Collection<EventRegistrationDto> fetchAll();
}
