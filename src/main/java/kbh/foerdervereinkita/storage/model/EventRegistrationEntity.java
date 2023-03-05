package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(EventRegistrationPK.class)
@Table(name = "event_registrations")
public class EventRegistrationEntity implements Serializable {

  @Column(name = "full_name", nullable = false, columnDefinition = "VARCHAR(255)")
  private String fullName;

  @Id
  @Column(name = "e_mail", nullable = false, columnDefinition = "VARCHAR(255)", unique = true)
  private String eMail;

  @Column(name = "comment", columnDefinition = "TEXT")
  private String comment;
}
