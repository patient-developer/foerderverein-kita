package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.service.EMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EMailServiceImpl implements EMailService {

  private final JavaMailSender emailSender;

  @Value("{spring.mail.username}")
  private final String fromEMail;

  @Override
  public void sendMail() {

    SimpleMailMessage message = new SimpleMailMessage();
//    message.setFrom(fromEMail);
    message.setTo("k.huthmacher@gmx.de");
    message.setCc("klaus.huthmacher@posteo.de");
    message.setSubject("!!! TEST !!!");
    message.setText("Hier steht der Text");

    emailSender.send(message);
  }
}
