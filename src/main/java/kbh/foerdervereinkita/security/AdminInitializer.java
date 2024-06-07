package kbh.foerdervereinkita.security;

import static kbh.foerdervereinkita.security.Authority.ADMIN;

import kbh.foerdervereinkita.users.UserDTO;
import kbh.foerdervereinkita.users.exception.UserException;
import kbh.foerdervereinkita.users.registration.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer {

  private final AdminConfig adminConfig;
  private final UserRegistrationService service;

  @EventListener(ApplicationReadyEvent.class)
  private void onStartup() {

    if (service.userExists(adminConfig.getName())) {
      log.info("Admin '{}' already initialized.", adminConfig.getName());
      return;
    }

    var user =
        UserDTO.builder()
            .name(adminConfig.getName())
            .password(adminConfig.getPassword())
            .authority(ADMIN)
            .enabled(true)
            .build();

    try {
      service.newUserAccount(user);
      log.info("Successfully initialized admin '{}'.", adminConfig.getName());
    } catch (UserException e) {
      log.error(e.getMessage(), e);
    }
  }
}
