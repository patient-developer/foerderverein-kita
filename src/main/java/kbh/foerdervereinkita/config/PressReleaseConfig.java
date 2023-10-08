package kbh.foerdervereinkita.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config.press-release")
public class PressReleaseConfig {
  private List<PressReleaseInfo> pressReleaseInfos;
}
