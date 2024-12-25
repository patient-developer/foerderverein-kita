package kbh.foerdervereinkita.service;

import java.nio.file.Path;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KindergartenService {

    @Value("${KINDERGARTEN_VIDEO}")
    private final String kindergartenVideo;

    private final ResourceLoader loader;

    public Mono<Resource> fetchVideo() {
        var location = Path.of(kindergartenVideo).toUri().toString();
        return Mono.fromSupplier(() -> loader.getResource(location));
    }
}
