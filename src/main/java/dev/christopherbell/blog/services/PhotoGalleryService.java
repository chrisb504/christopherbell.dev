package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.ImageProperties;
import dev.christopherbell.blog.models.photogallery.PhotoGalleryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Represents the service layer for handling photo related requests.
 */
@AllArgsConstructor
@Service
@Slf4j
public class PhotoGalleryService {

  private final ImageProperties imageProperties;

  /**
   * Get all existing photos.
   *
   * @return a PhotoGalleryResponse containing all existing photos.
   */
  public PhotoGalleryResponse getAllImages() {
    return PhotoGalleryResponse.builder()
        .images(imageProperties.getImages())
        .build();
  }
}