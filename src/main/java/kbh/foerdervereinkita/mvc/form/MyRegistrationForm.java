package kbh.foerdervereinkita.mvc.form;

import jakarta.validation.constraints.NotEmpty;
import kbh.foerdervereinkita.validate.ExistingEMail;
import kbh.foerdervereinkita.validate.FirstValidation;
import kbh.foerdervereinkita.validate.SecondValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyRegistrationForm {

  @NotEmpty(groups = FirstValidation.class)
  @ExistingEMail(groups = SecondValidation.class)
  private String email;
}
