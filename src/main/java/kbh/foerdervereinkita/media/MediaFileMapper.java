package kbh.foerdervereinkita.media;

import java.io.IOException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import kbh.foerdervereinkita.storage.model.MediaFileEntity;
import kbh.foerdervereinkita.storage.model.MediaFileProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class MediaFileMapper {

  @Autowired
  FileSecurity fileSecurity;

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  abstract MediaFileDTO toDTO(MediaFileProjection projection);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fileName", expression = "java(file.getOriginalFilename())")
  @Mapping(target = "fileData", expression = "java(fileSecurity.encrypt(file.getBytes()))")
  abstract MediaFileEntity toEntity(MultipartFile file)
          throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  abstract MediaFileModel toModel(MediaFileDTO dto);

  Resource toResource(MediaFileEntity entity)
          throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    var decryptedBytes = fileSecurity.decrypt(entity.getFileData());
    return new ByteArrayResource(decryptedBytes) {
      @Override
      public String getFilename() {
        return entity.getFileName();
      }
    };
  }
}
