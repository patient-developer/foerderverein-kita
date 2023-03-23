package kbh.foerdervereinkita.dto;

import java.time.LocalDate;

public record EventRegistrationDto(
    String fullName, String eMail, String comment, LocalDate feeValuationDate) {}
