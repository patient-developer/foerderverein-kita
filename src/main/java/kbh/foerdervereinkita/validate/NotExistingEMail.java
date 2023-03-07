package kbh.foerdervereinkita.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotExistingEMailValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistingEMail {

  String message() default "Es existiert bereits eine Anmeldung mit dieser E-Mail Adresse.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
