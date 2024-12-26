package kbh.foerdervereinkita.media;

import jakarta.transaction.Transactional;
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

  public List<MediaFileDTO> fetchAll() {
    return repository.findAllProjectedBy().stream().map(mapper::toDTO).toList();
  }

  public MediaFileDTO fetch(long id) {
    var entity = repository.findById(id);
    try {
      return mapper.toDTO(entity);
    } catch (IllegalBlockSizeException
        | BadPaddingException
        | InvalidKeyException
        | IOException e) {
      throw MediaFileException.loadFailure(entity.getFileName(), e);
    }
  }

  public Resource fetchResource(long id) {
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

  @Transactional
  public void updateDescription(long id, String description) {
    repository.updateDescription(id, description);
  }

  public String remove(long id) {
    var entity = repository.findById(id);
    repository.delete(entity);
    return entity.getFileName();
  }
}
