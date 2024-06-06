package kbh.foerdervereinkita.media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileStorage {

    @Value("${IMAGE_FILES_FOLDER}")
    private final String folder;

    public void persist(byte[] bytes, String fileName) throws IOException {
        var path = Path.of(folder, fileName);
        Files.write(path, bytes);
    }

    public Resource load(String fileName) throws IOException {
        var path = Path.of(folder, fileName);
        return new FileSystemResource(path.toFile());
    }

    public void remove(String fileName) throws IOException {
        var path = Path.of(folder, fileName);
        Files.delete(path);
    }
}
