package kbh.foerdervereinkita.media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileStorage {

  @Value("${IMAGE_FILES_FOLDER}")
  private final String folder;

  private final FileSecurity fileSecurity;

  public void persist(byte[] bytes, String fileName)
      throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    var encryptedPath = encryptedPath(fileName);
    var encryptedBytes = fileSecurity.encrypt(bytes);
    Files.write(encryptedPath, encryptedBytes);
  }

  public Resource load(String fileName)
      throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    var encryptedPath = encryptedPath(fileName);
    var decryptedBytes = fileSecurity.decrypt(Files.readAllBytes(encryptedPath));
    return new ByteArrayResource(decryptedBytes) {
      @Override
      public String getFilename() {
        return fileName;
      }
    };
  }

  public void remove(String fileName) throws IOException {
    var encryptedPath = encryptedPath(fileName);
    Files.delete(encryptedPath);
  }

  private Path encryptedPath(String fileName) {
    return FileSecurity.encryptedFileName(Path.of(folder, fileName));
  }
}
