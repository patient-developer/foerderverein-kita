package kbh.foerdervereinkita.security;

import jakarta.transaction.Transactional;
import kbh.foerdervereinkita.storage.model.UserEntity;
import kbh.foerdervereinkita.storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository repository;
  private final UserDetailsMapper mapper;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity entity =
        repository
            .findUserEntityByName(username)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        String.format("Failed to find user with name '%s'.", username)));

    return mapper.toImpl(entity);
  }
}
