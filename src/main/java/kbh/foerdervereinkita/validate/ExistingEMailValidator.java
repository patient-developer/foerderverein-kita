package kbh.foerdervereinkita.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kbh.foerdervereinkita.service.EventRegistrationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistingEMailValidator implements ConstraintValidator<ExistingEMail, String> {

  private EventRegistrationService service;

  @Override
  public void initialize(ExistingEMail constraintAnnotation) {
    // intentionally left empty
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {

    if (service.exists(email)) {
      return true;
    }

    context
        .buildConstraintViolationWithTemplate(
            "Es existiert keine Anmeldung mit dieser E-Mail Adresse.")
        .addConstraintViolation();

    return false;
  }
}
