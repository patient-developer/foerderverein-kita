package kbh.foerdervereinkita.storage.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Data
@Getter
@Setter
public class EventRegistrationPK implements Serializable {
  private String eMail;
}
