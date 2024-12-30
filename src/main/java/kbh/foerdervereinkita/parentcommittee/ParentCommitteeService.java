package kbh.foerdervereinkita.parentcommittee;

import java.util.Collection;
import kbh.foerdervereinkita.email.MailService;
import kbh.foerdervereinkita.email.MailServiceParameter;
import kbh.foerdervereinkita.email.ParentCommitteeMailConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentCommitteeService {

  private final ParentCommitteeMessageRepository messageRepository;
  private final ParentCommitteeMemberRepository memberRepository;
  private final ParentCommitteeMapper mapper;
  private final ParentCommitteeMailConfig mailConfig;

  @Qualifier("parent-committee")
  private final MailService mailService;

  public void persist(ParentCommitteeMessage message) {
    var entity = mapper.toEntity(message);
    messageRepository.save(entity);
    try {
      sendMail(message);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public Collection<ParentCommitteeMember> fetchParentCommitteeMembers() {
    return memberRepository.findAll().stream().map(mapper::toMember).toList();
  }

  private void sendMail(ParentCommitteeMessage message) {
    var parameter = toParameter(message);
    mailService.send(parameter);
  }

  private MailServiceParameter toParameter(ParentCommitteeMessage message) {
    return confirmationMail(message)
        ? toConfirmationMailParameter(message)
        : toNotificationMailParameter(message);
  }

  private static boolean confirmationMail(ParentCommitteeMessage message) {
    return StringUtils.hasText(message.mail());
  }

  private MailServiceParameter toConfirmationMailParameter(ParentCommitteeMessage message) {
    var text = confirmationText(message);
    return new MailServiceParameter(
        mailConfig.username(),
        message.mail().trim(),
        null,
        mailConfig.username(),
        "Ihr Anliegen",
        text);
  }

  private MailServiceParameter toNotificationMailParameter(ParentCommitteeMessage message) {
    var text = message.content();
    return new MailServiceParameter(
        mailConfig.username(), mailConfig.username(), null, null, "Anonyme Nachricht", text);
  }

  private static String confirmationText(ParentCommitteeMessage message) {
    return """
                        Vielen Dank für Ihre Nachricht, wir werden uns zeitnah bei Ihnen melden.

                        Ihre Mitteilung an uns:

                        #CONTENT#
                        """
        .replace("#CONTENT#", message.content());
  }
}
