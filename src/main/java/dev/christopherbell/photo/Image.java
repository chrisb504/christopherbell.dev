package dev.christopherbell.photo;

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
