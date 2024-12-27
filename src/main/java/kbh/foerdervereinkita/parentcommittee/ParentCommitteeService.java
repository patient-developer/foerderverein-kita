package kbh.foerdervereinkita.parentcommittee;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentCommitteeService {

  private final ParentCommitteeMessageRepository messageRepository;
  private final ParentCommitteeMemberRepository memberRepository;
  private final ParentCommitteeMapper mapper;

  public void persist(ParentCommitteeMessage message) {
    var entity = mapper.toEntity(message);
    messageRepository.save(entity);
  }

  public Collection<ParentCommitteeMember> fetchParentCommitteeMembers() {
    return memberRepository.findAll().stream().map(mapper::toMember).toList();
  }
}
