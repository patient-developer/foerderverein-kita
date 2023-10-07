package kbh.foerdervereinkita.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtrasService {

  @Value("${FLYER_FULL_FILENAME}")
  private final String flyerFullFilename;

  public Resource getFlyer() {
    return new FileSystemResource(flyerFullFilename);
  }
}
