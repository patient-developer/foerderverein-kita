package kbh.foerdervereinkita.dto;

import kbh.foerdervereinkita.auth.UserRole;

import java.util.Collection;

public record UserDto(
    Long userId, String username, String password, Collection<UserRole> roles, boolean enabled) {}
