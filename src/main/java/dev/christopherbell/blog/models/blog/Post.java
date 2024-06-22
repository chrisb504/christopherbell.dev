package dev.christopherbell.blog.models.blog;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Post {

  private String author;
  private String contentText;
  private String date;
  private String description;
  private Integer id;
  private String imagePath;
  private List<String> tags;
  private String title;
}