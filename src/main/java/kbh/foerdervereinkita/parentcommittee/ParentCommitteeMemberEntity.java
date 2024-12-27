package kbh.foerdervereinkita.parentcommittee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parent_committee_members")
public class ParentCommitteeMemberEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true, updatable = false)
  private long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "group_name", nullable = false)
  private String groupName;

  @Column(name = "image_file_name", nullable = false)
  private String imageFileName;

  @Column(name = "image_alternate_text", nullable = false)
  private String imageAlternateText;
}
