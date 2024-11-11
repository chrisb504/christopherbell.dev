package dev.christopherbell.photo;

import dev.christopherbell.photo.model.Photo;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class PhotoStub {

  public static String PHOTO_ID = "a6589389-fd29-47eb-b795-1d600862eab5";
  public static String PHOTO_NAME = "Little Red Miata";
  public static String PHOTO_DESCRIPTION = "The little red miata.";
  public static Instant NOW = Instant.now();

  public static Photo getPhotoStub() {
    return Photo.builder()
        .id(UUID.fromString(PHOTO_ID))
        .createdOn(NOW)
        .name(PHOTO_NAME)
        .description(PHOTO_DESCRIPTION)
        .build();
  }

  public static List<Photo> getPhotosStub() {
    return List.of(getPhotoStub(), getPhotoStub(), getPhotoStub());
  }
}
