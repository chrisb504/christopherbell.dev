package dev.christopherbell.blog;

import java.util.List;
import lombok.Builder;
import lombok.Data;

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