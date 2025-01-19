package kbh.foerdervereinkita.email;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public final class MailService {

  private final JavaMailSender sender;

  public void send(MailServiceParameter parameter) {
    var message = new SimpleMailMessage();
    message.setFrom(parameter.from());
    message.setTo(parameter.to());
    message.setText(parameter.text());
    message.setSubject(parameter.subject());
    if (StringUtils.hasText(parameter.cc())) {
      message.setCc(parameter.cc());
    }
    if (StringUtils.hasText(parameter.bcc())) {
      message.setBcc(parameter.bcc());
    }
    sender.send(message);
  }
}
