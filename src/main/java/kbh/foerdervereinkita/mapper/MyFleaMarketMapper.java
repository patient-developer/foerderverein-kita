package kbh.foerdervereinkita.mapper;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;
import kbh.foerdervereinkita.dto.MyFleaMarketDto;
import kbh.foerdervereinkita.mvc.form.MyRegistrationForm;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import kbh.foerdervereinkita.storage.model.StoringPositionLocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
    componentModel = "spring",
    imports = {StoringPositionLocationEntity.class, Objects.class})
public interface MyFleaMarketMapper {

  @Mapping(target = "hasPaid", ignore = true)
  @Mapping(target = "storingPositionLocationNumbers", ignore = true)
  @Mapping(target = "fullName", ignore = true)
  MyFleaMarketDto toDto(MyRegistrationForm form);

  @Transactional
  @Mapping(target = "fullName", source = "eventRegistration.fullName")
  @Mapping(target = "email", source = "eventRegistration.email")
  @Mapping(target = "hasPaid", expression = "java(eventRegistration.getFeeValuationDate() != null)")
  @Mapping(
      target = "storingPositionLocationNumbers",
      source = "eventRegistration",
      qualifiedByName = "mapStoringPositionLocationNumbers")
  MyFleaMarketDto toDto(EventRegistrationEntity eventRegistration);

  @Named("mapStoringPositionLocationNumbers")
  default Collection<Integer> mapStoringPositionLocationNumbers(EventRegistrationEntity entity) {
    return entity.getStoringPosition().getStoringPositionLocations().stream()
        .map(StoringPositionLocationEntity::getNumber)
        .filter(Objects::nonNull)
        .toList();
  }
}
