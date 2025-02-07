package kbh.foerdervereinkita.parentcommittee;

public record ParentCommitteeMessage(
    String mail, String phone, String content, boolean acceptedGdpr) {}
