package kbh.foerdervereinkita.service;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface StreamingService {

  Mono<Resource> fetchVideo(String filename);
}
