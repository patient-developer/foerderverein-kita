package kbh.foerdervereinkita.validate;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, FirstValidation.class, SecondValidation.class})
public interface ValidationSequence {}
