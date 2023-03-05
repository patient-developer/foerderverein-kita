package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "board_members")
public class BoardMemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "image_filename", nullable = false)
  private String imageFilename;

  @Lob
  @Column(name = "introduction", nullable = false)
  private String introduction;

  @Column(name = "rank", nullable = false)
  private Byte rank;
}
