package kbh.foerdervereinkita.media.exception;

public class MediaFileException extends RuntimeException {

    private MediaFileException(String msg) {
        super(msg);
    }

    public static MediaFileException alreadyExists(String fileName) {
        String message = String.format("Datei '%s' wurde bereits hochgeladen.", fileName);
        return new MediaFileException(message);
    }
}
