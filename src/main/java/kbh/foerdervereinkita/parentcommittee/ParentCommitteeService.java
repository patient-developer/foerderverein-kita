package kbh.foerdervereinkita.parentcommittee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentCommitteeService {

  private final ParentCommitteeMessageRepository repository;
  private final ParentCommitteeMapper mapper;

  public void persist(ParentCommitteeMessage message) {
    var entity = mapper.toEntity(message);
    repository.save(entity);
  }
}
