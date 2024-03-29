package kbh.foerdervereinkita.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public AuthenticationManager authenticationManager(
      HttpSecurity httpSecurity,
      UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder)
      throws Exception {

    return httpSecurity
        .getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder)
        .and()
        .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .authorizeHttpRequests()
        .requestMatchers("/internal/event-registrations")
        .hasAnyAuthority(UserRole.BOARD.name(), UserRole.ADMIN.name())
        .requestMatchers(
            "/",
            "/index",
            "/support/**",
            "/extras/**",
            "/sitemap-file",
            "/privacy",
            "/imprint",
            "/kueken",
            "/events",
            "/board",
            "/webjars/**",
            "/images/**",
            "/css/**",
            "/event-registrations",
            "/flohmarkt/**",
            "/error")
        .permitAll()
        .and()
        .formLogin()
        .defaultSuccessUrl("/index", true)
        .permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/index")
        .permitAll()
        .and()
        .build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
