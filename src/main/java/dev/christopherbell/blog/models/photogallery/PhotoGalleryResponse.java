package dev.christopherbell.blog.models.photogallery;

import dev.christopherbell.blog.models.global.Response;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class PhotoGalleryResponse extends Response {

  private List<Image> images;
}
