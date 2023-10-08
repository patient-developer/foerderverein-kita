package kbh.foerdervereinkita.dto;

import java.util.Collection;
import kbh.foerdervereinkita.auth.UserRole;

public record UserDto(
    Long userId, String username, String password, Collection<UserRole> roles, boolean enabled) {}
