package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pictures")
public class PictureEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "event_key", nullable = false, columnDefinition = "VARCHAR(255)")
  private String eventKey;

  @Column(name = "high_resolution_filename", columnDefinition = "VARCHAR(255)", unique = true)
  private String highResolutionFilename;

  @Column(
      name = "low_resolution_filename",
      nullable = false,
      columnDefinition = "VARCHAR(255)",
      unique = true)
  private String lowResolutionFilename;

  @Column(name = "rank", columnDefinition = "INTEGER", unique = true)
  private Integer rank;
}
