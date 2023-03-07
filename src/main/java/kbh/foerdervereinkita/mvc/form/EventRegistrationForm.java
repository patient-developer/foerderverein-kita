package kbh.foerdervereinkita.mvc.form;

import jakarta.validation.constraints.NotEmpty;
import kbh.foerdervereinkita.validate.FirstValidation;
import kbh.foerdervereinkita.validate.NotExistingEMail;
import kbh.foerdervereinkita.validate.SecondValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventRegistrationForm {

  @NotEmpty(groups = FirstValidation.class)
  private String fullName;

  @NotEmpty(groups = FirstValidation.class)
  @NotExistingEMail(groups = SecondValidation.class)
  private String eMail;

  private String comment;
}
