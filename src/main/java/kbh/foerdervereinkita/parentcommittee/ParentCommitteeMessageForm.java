package kbh.foerdervereinkita.parentcommittee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParentCommitteeMessageForm {
  String mail;
  String phone;
  String content;
  boolean acceptsGdpr;
}
