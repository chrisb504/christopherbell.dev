package dev.christopherbell.blog.models.photogallery;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhotoGalleryResponse {

  private List<Image> images;
}
