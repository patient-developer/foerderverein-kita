package kbh.foerdervereinkita.mvc.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventRegistrationForm {

  private String fullName;
  private String eMail;
  private String comment;
}
