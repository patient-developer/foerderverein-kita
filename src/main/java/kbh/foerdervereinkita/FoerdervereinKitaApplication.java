package kbh.foerdervereinkita;

import kbh.foerdervereinkita.config.PressReleaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PressReleaseConfig.class)
public class FoerdervereinKitaApplication {

  public static void main(String[] args) {
    SpringApplication.run(FoerdervereinKitaApplication.class, args);
  }
}
