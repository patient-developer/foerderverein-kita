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
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MediaFileService {

  private final MediaFileRepository repository;
  private final MediaFileMapper mapper;
  private final FileStorage fileStorage;

  public List<MediaFileDTO> fetchAll() {
    return repository.findAll().stream().map(mapper::toDTO).toList();
  }

  public Resource fetch(long id) {
    var entity = repository.findById(id);
    try {
      return fileStorage.load(entity.getFileName());
    } catch (IOException
        | IllegalBlockSizeException
        | BadPaddingException
        | InvalidKeyException e) {
      throw MediaFileException.loadFailure(entity.getFileName(), e);
    }
  }

  @Transactional
  public void persist(MultipartFile file) {

    if (repository.existsByFileName(file.getOriginalFilename())) {
      throw MediaFileException.alreadyExists(file.getOriginalFilename());
    }

    var entity = mapper.toEntity(file);
    repository.save(entity);

    try {
      fileStorage.persist(file.getBytes(), file.getOriginalFilename());
    } catch (IOException
        | InvalidKeyException
        | IllegalBlockSizeException
        | BadPaddingException e) {
      throw MediaFileException.writeFailure(file.getOriginalFilename(), e);
    }
  }

  @Transactional
  public String remove(long id) {
    var entity = repository.findById(id);
    repository.delete(entity);
    try {
      fileStorage.remove(entity.getFileName());
      return entity.getFileName();
    } catch (IOException e) {
      throw MediaFileException.deleteFailure(entity.getFileName(), e);
    }
  }
}
