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

  @Column private String mail;

  @Column private String phone;

  @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
  private String content;

  @Column(name = "accepted_gdpr", nullable = false)
  private boolean acceptedGdpr;

  @Column(nullable = false)
  private LocalDateTime timestamp;
}
