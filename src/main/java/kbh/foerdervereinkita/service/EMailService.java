package kbh.foerdervereinkita.service;

import kbh.foerdervereinkita.dto.EMailDto;

public interface EMailService {

  void sendMail(EMailDto eMail);
}
