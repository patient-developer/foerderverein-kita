package kbh.foerdervereinkita.parentcommittee;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parent_committee_messages")
public class ParentCommitteeMessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true, updatable = false)
  private long id;

  @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
  private String content;

  @Column(nullable = false)
  private LocalDateTime timestamp;
}
