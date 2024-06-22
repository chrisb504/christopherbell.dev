package dev.christopherbell.blog.models.blog;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogResponse {

  private List<Post> posts;
}
