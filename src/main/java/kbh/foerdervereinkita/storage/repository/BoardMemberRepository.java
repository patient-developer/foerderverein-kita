package kbh.foerdervereinkita.storage.repository;

import kbh.foerdervereinkita.storage.model.BoardMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMemberRepository extends JpaRepository<BoardMemberEntity, Long> {}
