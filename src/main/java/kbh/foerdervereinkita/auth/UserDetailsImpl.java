package kbh.foerdervereinkita.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private final Collection<? extends GrantedAuthority> authorities;
  private final String password;
  private final String username;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
}
