package kbh.foerdervereinkita.mapper;

import kbh.foerdervereinkita.mvc.model.BoardMemberModel;
import kbh.foerdervereinkita.storage.model.BoardMemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMemberMapper {

  BoardMemberModel toBoardMemberModel(BoardMemberEntity entity);
}
