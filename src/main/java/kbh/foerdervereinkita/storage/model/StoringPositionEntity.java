package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "storing_positions")
public class StoringPositionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "bulky_goods", columnDefinition = "TEXT")
  private String bulkyGoods;

  @NotNull
  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "event_registration_id", nullable = false)
  private EventRegistrationEntity eventRegistration;

  @OneToMany(mappedBy = "storingPosition", cascade = CascadeType.PERSIST)
  private Set<StoringPositionLocationEntity> storingPositionLocations = new LinkedHashSet<>();

  public void addStoringPositionLocation(StoringPositionLocationEntity storingPositionLocation) {
    storingPositionLocations.add(storingPositionLocation);
    storingPositionLocation.setStoringPosition(this);
  }
}
