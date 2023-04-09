package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.service.StoringPositionLocationService;
import kbh.foerdervereinkita.storage.repository.StoringPositionLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StoringPositionLocationServiceImpl implements StoringPositionLocationService {

  @Value("${AVAILABLE_STORING_POSITION_LOCATIONS_COUNT}")
  private final long availableStoringPositionLocationsCount;

  private final StoringPositionLocationRepository repository;

  @Override
  public long vacanciesCount() {
    return availableStoringPositionLocationsCount - repository.count();
  }
}
