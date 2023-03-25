package kbh.foerdervereinkita.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@AllArgsConstructor
public class Config {

  @Value("${ASSOCIATION_NAME}")
  private final String associationName;

  @Value("${ASSOCIATION_ADDRESS}")
  private final String associationAddress;

  @Value("${ASSOCIATION_LOGO_FILENAME}")
  private final String associationLogo;

  @Value("${CASH_ACCOUNT_IBAN}")
  private final String cashAccountIban;

  @Value("${CASH_ACCOUNT_CUSTODIAN}")
  private final String cashAccountCustodian;

  @Value("${KINDERGARTEN_LOGO_FILENAME}")
  private final String kindergartenLogo;

  @Value("${IMPRINT_REPRESENTATIVE}")
  private final String imprintRepresentative;

  @Value("${CONTACT_E_MAIL}")
  private final String contactEMail;

  @Value("${HOMEPAGE_URL}")
  private final String homepageUrl;

  @Value("${REGISTER_NUMBER}")
  private final String registerNumber;
}
