package kbh.foerdervereinkita.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

  @Value("${DECLARATION_OF_MEMBERSHIP_FILENAME}")
  private final String declarationOfMembershipFilename;

  public Resource getDeclarationOfMembership() {
    return new ClassPathResource(declarationOfMembershipFilename);
  }
}
