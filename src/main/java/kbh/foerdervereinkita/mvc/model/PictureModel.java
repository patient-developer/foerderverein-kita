package kbh.foerdervereinkita.mvc.model;

import lombok.NonNull;

public record PictureModel(
    @NonNull String lowResolutionFilename, @NonNull String highResolutionFilename) {}
