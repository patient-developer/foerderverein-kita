package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "event_registrations",
    indexes = {@Index(name = "e_mail", columnList = "e_mail", unique = true)})
public class EventRegistrationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 255)
  @NotNull
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Size(max = 255)
  @NotNull
  @Column(name = "e_mail", nullable = false)
  private String email;

  @Column(name = "comment", columnDefinition = "TEXT")
  private String comment;

  @NotNull
  @Column(name = "registration_timestamp", nullable = false)
  private Instant registrationTimestamp;

  @Column(name = "fee_valuation_date", columnDefinition = "DATE")
  private LocalDate feeValuationDate;
}
