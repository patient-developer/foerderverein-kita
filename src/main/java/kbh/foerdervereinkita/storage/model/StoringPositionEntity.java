package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "storing_position")
public class StoringPositionEntity {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotNull
  @Column(name = "number", nullable = false)
  private Integer number;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "event_registration_id", nullable = false)
  private EventRegistrationEntity eventRegistration;
}
