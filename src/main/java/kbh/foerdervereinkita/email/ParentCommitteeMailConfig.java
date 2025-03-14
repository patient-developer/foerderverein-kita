package kbh.foerdervereinkita.email;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail.parent-committee")
public record ParentCommitteeMailConfig(
    String host, int port, String username, String password, Map<String, String> properties) {}
