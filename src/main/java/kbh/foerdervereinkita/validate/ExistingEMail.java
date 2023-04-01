package kbh.foerdervereinkita.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistingEMailValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistingEMail {

  String message() default "Es existiert keine Anmeldung mit dieser E-Mail Adresse.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
