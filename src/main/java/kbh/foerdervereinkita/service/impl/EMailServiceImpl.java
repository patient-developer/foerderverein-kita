package kbh.foerdervereinkita.service.impl;

import kbh.foerdervereinkita.config.Config;
import kbh.foerdervereinkita.dto.EMailDto;
import kbh.foerdervereinkita.service.EMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class EMailServiceImpl implements EMailService {

  private final Config config;
  private final JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private final String fromEMail;

  @Override
  public void sendMail(EMailDto eMail) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(fromEMail);
    message.setTo(eMail.eMail());
    message.setBcc(fromEMail);
    message.setSubject("Bestätigung Anmeldung Flohmarkt");
    message.setText(concatMailText(eMail.fullName(), eMail.comment()));

    emailSender.send(message);
  }

  private String concatMailText(String fullName, String comment) {

    return """
        Hallo $fullName,

        vielen Dank für Ihre Anmeldung bei unserem Flohmarkt.
        $comment
        Anbei die wichtigsten Informationen zusammengefasst:

        Der Flohmarkt findet am
          22. April 2023
          von 13 bis 16 Uhr
          in der Turnhalle der Grundschule Sonnenfeld Homburg
        statt.

        Beachten Sie, dass wir ausschließlich Stellplätze zur Verfügung stellen, d.h. Tische o.ä. müssen selbst mitgebracht werden.

        Aufbau ist ab 11 Uhr möglich.

        Bitte überweisen Sie die Standgebühr in Höhe von 10 EUR (7 EUR mit Kuchen) pro 3m Stand im Voraus auf unser Konto:

        Empfänger  $associationName
        IBAN       $iban ($cashAccountCustodian)

        Bei Rückfragen antworten Sie gerne einfach auf diese E-Mail.

        Herzliche Grüße,
          Nastassja, David, Klaus, Lena und Judith


        ---
        Kontakt  $contactEMail
        Homepage $homepageUrl
        """
        .replace("$fullName", fullName)
        .replace("$associationName", config.getAssociationName())
        .replace("$iban", StringUtils.trimAllWhitespace(config.getCashAccountIban()))
        .replace("$cashAccountCustodian", config.getCashAccountCustodian())
        .replace("$contactEMail", config.getContactEMail())
        .replace("$homepageUrl", config.getHomepageUrl())
        .replace("$comment", resolveComment(comment));
  }

  private String resolveComment(String comment) {
    return (comment == null || comment.isBlank())
        ? ""
        : "\nIhr Kommentar an uns:\n" + comment + "\n";
  }
}
