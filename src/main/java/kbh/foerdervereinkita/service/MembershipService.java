package kbh.foerdervereinkita.service;

import kbh.foerdervereinkita.config.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

  private final Config config;

  public String getDeclarationOfMembership() {
    ClassPathResource resource = new ClassPathResource(config.getDeclarationOfMembershipFilename());
    return resource.getPath();
  }
}
