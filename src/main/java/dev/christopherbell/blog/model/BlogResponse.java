package dev.christopherbell.blog.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogResponse {

  private List<Post> posts;
}
