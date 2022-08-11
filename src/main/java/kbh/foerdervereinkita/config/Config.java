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

  @Value("${FIRST_BOARD_MEMBER_FULL_NAME}")
  private final String firstBoardMemberFullName;

  @Value("${FIRST_BOARD_MEMBER_IMAGE_FILENAME}")
  private final String firstBoardMemberImageFilename;

  @Value("${FIRST_BOARD_MEMBER_TEXT}")
  private final String firstBoardMemberText;

  @Value("${SECOND_BOARD_MEMBER_FULL_NAME}")
  private final String secondBoardMemberFullName;

  @Value("${SECOND_BOARD_MEMBER_IMAGE_FILENAME}")
  private final String secondBoardMemberImageFilename;

  @Value("${SECOND_BOARD_MEMBER_TEXT}")
  private final String secondBoardMemberText;

  @Value("${CONTACT_E_MAIL}")
  private final String contactEMail;

  @Value("${REGISTER_NUMBER}")
  private final String registerNumber;

  @Value("${DECLARATION_OF_MEMBERSHIP_FILENAME}")
  private final String declarationOfMembershipFilename;
}
