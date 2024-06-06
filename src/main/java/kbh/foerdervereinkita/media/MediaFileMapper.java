package kbh.foerdervereinkita.media;

import kbh.foerdervereinkita.storage.model.MediaFileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MediaFileMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  MediaFileDTO toDTO(MediaFileEntity entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "fileName", expression = "java(file.getOriginalFilename())")
  MediaFileEntity toEntity(MultipartFile file);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "fileName", source = "fileName")
  MediaFileModel toModel(MediaFileDTO dto);
}
