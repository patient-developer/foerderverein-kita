package kbh.foerdervereinkita.parentcommittee;

import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = LocalDateTime.class)
public interface ParentCommitteeMapper {

  @Mapping(target = "mail", source = "mail")
  @Mapping(target = "phone", source = "phone")
  @Mapping(target = "content", expression = "java(form.getContent().trim())")
  @Mapping(target = "acceptedGdpr", source = "acceptsGdpr")
  ParentCommitteeMessage toMessage(ParentCommitteeMessageForm form);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "mail", source = "mail")
  @Mapping(target = "phone", source = "phone")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "acceptedGdpr", source = "acceptedGdpr")
  @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
  ParentCommitteeMessageEntity toEntity(ParentCommitteeMessage message);

  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "groupName", source = "groupName")
  @Mapping(target = "imageFileName", source = "imageFileName")
  ParentCommitteeMember toMember(ParentCommitteeMemberEntity entity);

  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "groupName", source = "groupName")
  @Mapping(target = "imageFileName", source = "imageFileName")
  @Mapping(
      target = "imageAlternateText",
      expression =
          "java(String.format(\"Kita Sonnenfeld Elternausschuss %s %s\", member.groupName(), member.fullName()))")
  ParentCommitteeMemberModel toModel(ParentCommitteeMember member);
}
