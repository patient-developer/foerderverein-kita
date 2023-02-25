package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.dto.UserDto;
import kbh.foerdervereinkita.mapper.UserDetailsMapper;
import kbh.foerdervereinkita.service.UserService;
import kbh.foerdervereinkita.storage.model.UserEntity;
import kbh.foerdervereinkita.storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repository;
  private final UserDetailsMapper mapper;

  @Override
  public boolean exists(String username) {
    return repository.existsByUsername(username);
  }

  @Override
  public void persist(UserDto user) {

    UserEntity entity = mapper.toUserEntity(user);

    repository.save(entity);
  }
}
