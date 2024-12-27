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

  @Mapping(target = "content", expression = "java(form.getContent().trim())")
  ParentCommitteeMessage toMessage(ParentCommitteeMessageForm form);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "content", source = "content")
  @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
  ParentCommitteeMessageEntity toEntity(ParentCommitteeMessage message);

  @Mapping(target = "fullName", source = "fullName")
  @Mapping(target = "groupName", source = "groupName")
  @Mapping(target = "imageFileName", source = "imageFileName")
  @Mapping(target = "imageAlternateText", source = "imageAlternateText")
  ParentCommitteeMember toMember(ParentCommitteeMemberEntity entity);
}
