package kbh.foerdervereinkita.service;

import kbh.foerdervereinkita.dto.UserDto;

public interface UserService {
  boolean exists(String username);

  void persist(UserDto user);
}
