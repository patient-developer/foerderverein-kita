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

@Service
@RequiredArgsConstructor
public class MediaFileService {

  private final MediaFileRepository repository;
  private final MediaFileMapper mapper;
  private final FileSecurity fileSecurity;

  public List<MediaFileDTO> fetchAll() {
    return repository.findAllProjectedBy().stream().map(mapper::toDTO).toList();
  }

  public Resource fetch(long id) {
    var entity = repository.findById(id);
    try {
      return mapper.toResource(entity);
    } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
      throw MediaFileException.loadFailure(entity.getFileName(), e);
    }
  }

  public void persist(MediaFileDTO dto) {

    if (repository.existsByFileName(dto.fileName())) {
      throw MediaFileException.alreadyExists(dto.fileName());
    }

    try {
      var entity = mapper.toEntity(dto);
      repository.save(entity);
    } catch (IllegalBlockSizeException
             | BadPaddingException
             | IOException
             | InvalidKeyException e) {
      throw MediaFileException.writeFailure(dto.fileName(), e);
    }
  }

  public String remove(long id) {
    var entity = repository.findById(id);
    repository.delete(entity);
    return entity.getFileName();
  }
}
