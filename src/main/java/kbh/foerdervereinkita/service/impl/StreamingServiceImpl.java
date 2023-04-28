package kbh.foerdervereinkita.service.impl;

import java.nio.file.Path;
import kbh.foerdervereinkita.service.StreamingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
class StreamingServiceImpl implements StreamingService {

  @Value("${VIDEOS_FOLDER}")
  private String videoFolder;

  private ResourceLoader resourceLoader;

  @Override
  public Mono<Resource> fetchVideo(String filename) {

    String location = Path.of(videoFolder, filename).toUri().toString();

    return Mono.just(resourceLoader.getResource(location));
  }
}
