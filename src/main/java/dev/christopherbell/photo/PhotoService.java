package dev.christopherbell.photo;

import dev.christopherbell.photo.model.PhotoProperties;
import dev.christopherbell.photo.model.PhotoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Represents the service layer for handling photo related requests.
 */
@AllArgsConstructor
@Service
@Slf4j
public class PhotoService {

  private PhotoProperties photoProperties;

  /**
   * Get all existing photos.
   *
   * @return a PhotoGalleryResponse containing all existing photos.
   */
  public PhotoResponse getAllImages() {
    return PhotoResponse.builder()
        .images(photoProperties.getPhotos())
        .build();
  }
}