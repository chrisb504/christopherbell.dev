package dev.christopherbell.blog.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Post {

  private String author;
  private String contentText;
  private Instant createdOn;
  private String description;
  private UUID id;
  private String imagePath;
  private List<String> tags;
  private String title;
}