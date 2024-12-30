package kbh.foerdervereinkita.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailServiceConfig {

  @Bean(name = "parent-committee")
  public MailService parentCommitteeMailService(ParentCommitteeMailConfig config) {
    var sender = new JavaMailSenderImpl();
    sender.setHost(config.host());
    sender.setPort(config.port());
    sender.setUsername(config.username());
    sender.setPassword(config.password());
    var properties = sender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    return new MailService(sender);
  }
}
