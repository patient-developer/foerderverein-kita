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

  @Value("${LOGO_FILENAME}")
  private final String logo;

  @Value("${IMPRINT_REPRESENTATIVE}")
  private final String imprintRepresentative;

  @Value("${CONTACT_E_MAIL}")
  private final String contactEMail;

  @Value("${REGISTER_NUMBER}")
  private final String registerNumber;

  @Value("${DECLARATION_OF_MEMBERSHIP_FILENAME}")
  private final String declarationOfMembershipFilename;

  @Value("${SITEMAP_FILENAME}")
  private final String sitemapFilename;
}
