package dev.christopherbell.blog.models.photogallery;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Image {

  private String dateAdded;
  private String desc;
  private int id;
  private String name;
  private String path;
}
