package dev.christopherbell.photo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PhotoGalleryServiceTest {
  private PhotoGalleryService photoGalleryService;
  @Autowired
  private ImageProperties imageProperties;

  @BeforeEach
  public void init() {
    photoGalleryService = new PhotoGalleryService(imageProperties);
  }

  @Test
  public void testGetAllImages_success() {
    var images = photoGalleryService.getAllImages();

    Assertions.assertEquals(images.getImages().size(), imageProperties.getImages().size());
  }
}
