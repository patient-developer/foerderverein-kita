package kbh.foerdervereinkita.media;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import kbh.foerdervereinkita.storage.model.MediaFileEntity;
import kbh.foerdervereinkita.storage.model.MediaFileProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class MediaFileMapper {

  @Autowired FileSecurity fileSecurity;

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "fileData", expression = "java(fileSecurity.decrypt(entity.getFileData()))")
  abstract MediaFileDTO toDTO(MediaFileEntity entity)
      throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  @Mapping(target = "description", source = "description", qualifiedByName = "shrinkDescription")
  @Mapping(target = "fileData", ignore = true)
  abstract MediaFileDTO toDTO(MediaFileProjection projection);

  @Named("shrinkDescription")
  String shrinkDescription(String description) {
    if (description == null || description.isEmpty()) {
      return null;
    }

    return description.length() > 30 ? description.substring(0, 30) + " ..." : description;
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fileName", expression = "java(form.getFile().getOriginalFilename())")
  @Mapping(target = "description", source = "description", defaultExpression = "java(\"\")")
  @Mapping(target = "fileData", expression = "java(form.getFile().getBytes())")
  abstract MediaFileDTO toDTO(MediaFileForm form) throws IOException;

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fileName", source = "fileName")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "fileData", expression = "java(fileSecurity.encrypt(dto.fileData()))")
  abstract MediaFileEntity toEntity(MediaFileDTO dto)
      throws IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  @Mapping(target = "description", source = "description")
  @Mapping(
      target = "base64EncodedImage",
      source = "fileData",
      qualifiedByName = "toBase64EncodedImage")
  abstract MediaFileModel toModel(MediaFileDTO dto);

  @Named("toBase64EncodedImage")
  String toBase64EncodedImage(byte[] fileData) {
    return null == fileData ? null : Base64.getEncoder().encodeToString(fileData);
  }

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
