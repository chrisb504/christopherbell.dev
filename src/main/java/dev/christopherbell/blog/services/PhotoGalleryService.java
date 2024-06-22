package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.Constants;
import dev.christopherbell.blog.configs.properties.ImageProperties;
import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.photogallery.PhotoGalleryResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PhotoGalleryService {

  private final ImageProperties imageProperties;

  public PhotoGalleryResponse getAllImages(HttpServletRequest request) {
    final var images = imageProperties.getImages();
    log.info("Request's address: {}", request.getRemoteAddr());
    if (Objects.isNull(images)) {
      log.error("No references to images found in config file");
      var messages = List.of(new Message("PhotoGalleryService.Response.NoImages", "No Images Found"));
      return PhotoGalleryResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    return PhotoGalleryResponse.builder()
        .images(images)
        .status(Constants.STATUS_SUCCESS)
        .build();
  }
}