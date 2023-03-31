package kbh.foerdervereinkita.mapper;

import jakarta.transaction.Transactional;
import kbh.foerdervereinkita.dto.MyFleaMarketDto;
import kbh.foerdervereinkita.mvc.form.MyRegistrationForm;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import kbh.foerdervereinkita.storage.model.StoringPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyFleaMarketMapper {

  @Mapping(target = "hasPaid", ignore = true)
  @Mapping(target = "storingPositionNumber", ignore = true)
  @Mapping(target = "fullName", ignore = true)
  MyFleaMarketDto toDto(MyRegistrationForm form);

  @Transactional
  @Mapping(target = "fullName", source = "eventRegistration.fullName")
  @Mapping(target = "email", source = "eventRegistration.email")
  @Mapping(target = "hasPaid", expression = "java(eventRegistration.getFeeValuationDate() != null)")
  @Mapping(target = "storingPositionNumber", source = "storingPosition.number")
  MyFleaMarketDto toDto(
      EventRegistrationEntity eventRegistration, StoringPositionEntity storingPosition);
}
