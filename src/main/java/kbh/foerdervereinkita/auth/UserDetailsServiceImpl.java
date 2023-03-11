package kbh.foerdervereinkita.auth;

import jakarta.transaction.Transactional;
import kbh.foerdervereinkita.mapper.UserDetailsMapper;
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

    return repository
        .findByUsername(username)
        .map(mapper::toUserDetailsImpl)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
