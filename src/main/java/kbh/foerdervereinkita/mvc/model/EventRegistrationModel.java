package kbh.foerdervereinkita.mvc.model;

import java.time.LocalDate;

public record EventRegistrationModel(
    String fullName, String email, String comment, LocalDate feeValuationDate) {}
