package kbh.foerdervereinkita;

import kbh.foerdervereinkita.email.ParentCommitteeMailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories
@EnableConfigurationProperties(ParentCommitteeMailConfig.class)
public class FoerdervereinKitaApplication {

  public static void main(String[] args) {
    SpringApplication.run(FoerdervereinKitaApplication.class, args);
  }
}
