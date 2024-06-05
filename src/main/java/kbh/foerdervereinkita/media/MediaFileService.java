package kbh.foerdervereinkita.media;

import java.util.List;

import kbh.foerdervereinkita.media.exception.MediaFileException;
import kbh.foerdervereinkita.storage.repository.MediaFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MediaFileService {

    private final MediaFileRepository repository;
    private final MediaFileMapper mapper;

    public List<MediaFileDTO> fetchAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public void persist(MultipartFile file) {

        if (repository.existsByFileName(file.getOriginalFilename())) {
            throw MediaFileException.alreadyExists(file.getOriginalFilename());
        }

        var entity = mapper.toEntity(file);
        repository.save(entity);
    }
}
