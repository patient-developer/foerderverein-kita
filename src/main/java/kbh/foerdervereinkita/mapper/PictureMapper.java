package kbh.foerdervereinkita.mapper;

import kbh.foerdervereinkita.mvc.model.PictureModel;
import kbh.foerdervereinkita.storage.model.PictureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PictureMapper {

  @Mapping(target = "lowResolutionFilename", source = "lowResolutionFilename")
  @Mapping(target = "highResolutionFilename", source = "highResolutionFilename")
  PictureModel toModel(PictureEntity entity);
}
