package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "storing_position_locations",
    indexes = {@Index(name = "number", columnList = "number", unique = true)})
public class StoringPositionLocationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "number")
  private Integer number;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "storing_position_id", nullable = false)
  private StoringPositionEntity storingPosition;
}
