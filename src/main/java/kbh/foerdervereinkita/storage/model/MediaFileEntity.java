package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "media_files")
public class MediaFileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "file_name", nullable = false, unique = true)
  private String fileName;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Lob
  @Column(name = "file_data", nullable = false, columnDefinition = "MEDIUMBLOB")
  private byte[] fileData;
}
