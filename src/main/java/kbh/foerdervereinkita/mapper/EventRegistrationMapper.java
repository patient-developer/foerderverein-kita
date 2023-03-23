package kbh.foerdervereinkita.mapper;

import kbh.foerdervereinkita.dto.EventRegistrationDto;
import kbh.foerdervereinkita.mvc.form.EventRegistrationForm;
import kbh.foerdervereinkita.mvc.model.EventRegistrationModel;
import kbh.foerdervereinkita.storage.model.EventRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    imports = {java.time.LocalDateTime.class, java.time.ZoneId.class})
public interface EventRegistrationMapper {

  @Mapping(target = "eMail", expression = "java(form.getEMail().trim())")
  @Mapping(target = "feeValuationDate", ignore = true)
  EventRegistrationDto toDto(EventRegistrationForm form);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "email", expression = "java(dto.eMail().trim())")
  @Mapping(
      target = "registrationTimestamp",
      expression = "java(LocalDateTime.now().atZone(ZoneId.of(\"Europe/Paris\")).toInstant())")
  EventRegistrationEntity toEntity(EventRegistrationDto dto);

  @Mapping(target = "eMail", source = "email")
  EventRegistrationDto toDto(EventRegistrationEntity entity);

  @Mapping(target = "email", source = "eMail")
  EventRegistrationModel toModel(EventRegistrationDto dto);
}
