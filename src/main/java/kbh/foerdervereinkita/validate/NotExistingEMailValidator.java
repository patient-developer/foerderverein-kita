package kbh.foerdervereinkita.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kbh.foerdervereinkita.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotExistingEMailValidator implements ConstraintValidator<NotExistingEMail, String> {

  private final EventRegistrationService service;

  @Override
  public void initialize(NotExistingEMail constraintAnnotation) {
    // intentionally left empty
  }

  @Override
  public boolean isValid(String eMail, ConstraintValidatorContext context) {

    if (!service.exists(eMail)) {
      return true;
    }

    context.disableDefaultConstraintViolation();

    context
        .buildConstraintViolationWithTemplate(
            "Es existiert bereits eine Anmeldung mit dieser E-Mail Adresse.")
        .addConstraintViolation();

    return false;
  }
}
