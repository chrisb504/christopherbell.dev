package dev.christopherbell.blog.models.blog;

import lombok.Data;

@Data
public class BlogRequest {

  private String author;
  private String contentText;
  private String description;
  private String imagePath;
  private String tags;
  private String title;
}
