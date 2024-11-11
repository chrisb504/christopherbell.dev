package dev.christopherbell.photo;

import static org.mockito.Mockito.when;

import dev.christopherbell.photo.model.PhotoProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceTest {

  private PhotoService photoService;
  @Mock
  private PhotoProperties photoProperties;

  @BeforeEach
  public void init() {
    photoService = new PhotoService(photoProperties);
  }

  @Test
  public void testGetAllImages_success() {

    when(photoProperties.getPhotos()).thenReturn(PhotoStub.getPhotosStub());

    var images = photoService.getAllImages();

    Assertions.assertEquals(images.getImages().size(), photoProperties.getPhotos().size());
  }
}
