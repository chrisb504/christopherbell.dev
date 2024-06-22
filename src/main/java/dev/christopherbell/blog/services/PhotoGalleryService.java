package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.Constants;
import dev.christopherbell.blog.configs.properties.ImageProperties;
import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.photogallery.PhotoGalleryResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhotoGalleryService {

  private final ImageProperties imageProperties;

  @Autowired
  public PhotoGalleryService(ImageProperties imageProperties) {
    this.imageProperties = imageProperties;
  }

  public PhotoGalleryResponse getAllImages(HttpServletRequest request) {
    final var images = imageProperties.getImages();
    log.info("Request's address: {}", request.getRemoteAddr());
    if (Objects.isNull(images)) {
      log.error("No references to images found in config file");
      var messages = List.of(new Message("PhotoGalleryService.Response.NoImages", "No Images Found"));
      return new PhotoGalleryResponse(null, messages, Constants.STATUS_FAILURE);
    }
    return new PhotoGalleryResponse(images, null, Constants.STATUS_SUCCESS);
  }
}