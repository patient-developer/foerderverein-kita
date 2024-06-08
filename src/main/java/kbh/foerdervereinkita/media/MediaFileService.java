package kbh.foerdervereinkita.media;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import kbh.foerdervereinkita.media.exception.MediaFileException;
import kbh.foerdervereinkita.storage.repository.MediaFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MediaFileService {

  private final MediaFileRepository repository;
  private final MediaFileMapper mapper;
  private final FileSecurity fileSecurity;

  public List<MediaFileDTO> fetchAll() {
    return repository.findAll().stream().map(mapper::toDTO).toList();
  }

  public Resource fetch(long id) {
    var entity = repository.findById(id);
    try {
      return mapper.toResource(entity);
    } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
      throw MediaFileException.loadFailure(entity.getFileName(), e);
    }
  }

  public void persist(MultipartFile file) {

    if (repository.existsByFileName(file.getOriginalFilename())) {
      throw MediaFileException.alreadyExists(file.getOriginalFilename());
    }

    try {
      var entity = mapper.toEntity(file);
      repository.save(entity);
    } catch (IllegalBlockSizeException
             | BadPaddingException
             | IOException
             | InvalidKeyException e) {
      throw MediaFileException.writeFailure(file.getOriginalFilename(), e);
    }
  }

  public String remove(long id) {
    var entity = repository.findById(id);
    repository.delete(entity);
    return entity.getFileName();
  }
}
