package dev.christopherbell.photo;

import dev.christopherbell.photo.model.PhotoProperties;
import dev.christopherbell.photo.model.PhotoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service layer for photo gallery operations.
 */
@AllArgsConstructor
@Service
@Slf4j
public class PhotoService {

  private PhotoProperties photoProperties;

  /**
   * Retrieves all configured photos.
   *
   * @return a {@link PhotoResponse} with all photos
   */
  public PhotoResponse getAllImages() {
    return PhotoResponse.builder()
        .images(photoProperties.getPhotos())
        .build();
  }
}
