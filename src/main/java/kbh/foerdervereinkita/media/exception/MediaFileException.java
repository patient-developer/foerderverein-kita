package kbh.foerdervereinkita.media.exception;

public class MediaFileException extends RuntimeException {

  private MediaFileException(String msg) {
    super(msg);
  }

  private MediaFileException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public static MediaFileException alreadyExists(String fileName) {
    var message = String.format("Datei '%s' wurde bereits hochgeladen.", fileName);
    return new MediaFileException(message);
  }

  public static MediaFileException loadFailure(String fileName, Throwable cause) {
    var message = String.format("Datei '%s' kann nicht geladen werden.", fileName);
    return new MediaFileException(message, cause);
  }

  public static MediaFileException writeFailure(String fileName, Throwable cause) {
    var message = String.format("Datei '%s' konnte nicht gespeichert werden.", fileName);
    return new MediaFileException(message, cause);
  }

  public static MediaFileException deleteFailure(String fileName, Throwable cause) {
    var message = String.format("Date '%s' konnte nicht gelöscht werden.", fileName);
    return new MediaFileException(message, cause);
  }
}
