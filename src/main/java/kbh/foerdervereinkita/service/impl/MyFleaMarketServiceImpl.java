package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.dto.MyFleaMarketDto;
import kbh.foerdervereinkita.mapper.MyFleaMarketMapper;
import kbh.foerdervereinkita.service.MyFleaMarketService;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import kbh.foerdervereinkita.storage.model.StoringPositionEntity;
import kbh.foerdervereinkita.storage.repository.EventRegistrationRepository;
import kbh.foerdervereinkita.storage.repository.StoringPositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class MyFleaMarketServiceImpl implements MyFleaMarketService {

  private EventRegistrationRepository eventRegistrationRepository;
  private StoringPositionRepository storingPositionRepository;
  private MyFleaMarketMapper mapper;

  @Override
  public MyFleaMarketDto fetch(MyFleaMarketDto myFleaMarket) {

    EventRegistrationEntity eventRegistration =
        eventRegistrationRepository.findByEmail(myFleaMarket.email());

    StoringPositionEntity storingPosition =
        storingPositionRepository.findByEventRegistration_Email(myFleaMarket.email()).orElse(null);

    return mapper.toDto(eventRegistration, storingPosition);
  }
}
