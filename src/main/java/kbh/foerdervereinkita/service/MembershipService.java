package kbh.foerdervereinkita.service;

import kbh.foerdervereinkita.config.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

  private final Config config;

  public FileSystemResource getDeclarationOfMembership() {
    return new FileSystemResource(config.getDeclarationOfMembershipFilename());
  }
}
