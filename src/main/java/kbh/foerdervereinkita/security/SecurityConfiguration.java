package kbh.foerdervereinkita.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(
                        "/",
                        "/index",
                        "/support/**",
                        "/extras/**",
                        "/privacy",
                        "/imprint",
                        "/kueken",
                        "/events",
                        "/flohmarkt/**",
                                "/kindergarten/**",
                        "/board",
                        "/login",
                        "/error",
                        "/webjars/**",
                        "/images/**",
                        "/css/**",
                        "/sitemap-file")
                    .permitAll()
                    .requestMatchers("/users/**")
                    .hasAuthority(Authority.ADMIN.name())
                    .requestMatchers("/media/**")
                    .hasAnyAuthority(
                        Authority.ADMIN.name(),
                        Authority.BOARD.name(),
                        Authority.CHILDCARE_WORKER.name()))
        .formLogin(login -> login.defaultSuccessUrl("/", true).permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/").permitAll())
        .build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
