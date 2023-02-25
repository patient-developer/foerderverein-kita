package kbh.foerdervereinkita.service;

import kbh.foerdervereinkita.auth.UserRole;
import kbh.foerdervereinkita.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ApplicationSetupService implements ApplicationListener<ContextRefreshedEvent> {

  private final UserService userService;
  private final UserRoleService userRoleService;

  @Value("${spring.security.user.name}")
  private final String adminUsername;

  @Value("${spring.security.user.password}")
  private final String adminPassword;

  @Value("${spring.security.user.roles}")
  private final String adminRole;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    userRoleService.initUserRoles();
    persistAdminIfNotExists();
  }

  private void persistAdminIfNotExists() {

    if (!userService.exists(adminUsername)) {

      UserDto admin = createAdmin();

      userService.persist(admin);
    }
  }

  private UserDto createAdmin() {

    UserRole role = UserRole.valueOf(adminRole);

    return new UserDto(null, adminUsername, adminPassword, Collections.singleton(role), true);
  }
}
