package kbh.foerdervereinkita.mapper;

import kbh.foerdervereinkita.dto.EMailDto;
import kbh.foerdervereinkita.dto.EventRegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EMailMapper {

  EMailDto toDto(EventRegistrationDto eventRegistration);
}
