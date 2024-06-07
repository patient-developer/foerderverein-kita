package kbh.foerdervereinkita.users;

import kbh.foerdervereinkita.security.Authority;
import lombok.Builder;

@Builder
public record UserDTO(
    Long id, String name, String password, Authority authority, boolean enabled) {}
