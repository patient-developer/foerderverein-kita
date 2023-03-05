package kbh.foerdervereinkita.mapper;

import kbh.foerdervereinkita.dto.EventRegistrationDto;
import kbh.foerdervereinkita.mvc.form.EventRegistrationForm;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventRegistrationMapper {

  @Mapping(target = "eMail", source = "EMail")
  EventRegistrationDto toDto(EventRegistrationForm form);

  @Mapping(target = "EMail", source = "eMail")
  EventRegistrationEntity toEntity(EventRegistrationDto dto);
}
