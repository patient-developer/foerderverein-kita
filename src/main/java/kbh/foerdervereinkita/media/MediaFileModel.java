package kbh.foerdervereinkita.media;

public record MediaFileModel(
        long id, String fileName, String description, String base64EncodedImage) {
}
