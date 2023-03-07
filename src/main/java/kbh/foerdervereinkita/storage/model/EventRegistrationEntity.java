package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
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

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "e_mail", nullable = false)
  private String email;

  @Lob
  @Column(name = "comment")
  private String comment;
}
