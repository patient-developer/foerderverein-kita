package kbh.foerdervereinkita.email;

public record MailServiceParameter(
    String from, String to, String cc, String bcc, String subject, String text) {}
