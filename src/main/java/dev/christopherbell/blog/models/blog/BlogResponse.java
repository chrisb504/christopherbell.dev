package dev.christopherbell.blog.models.blog;

import java.util.List;

import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.global.Response;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class BlogResponse extends Response {

  private Post post;
  private List<Post> posts;
  private List<String> tags;
}
