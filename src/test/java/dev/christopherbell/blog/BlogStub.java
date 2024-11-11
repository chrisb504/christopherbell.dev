package dev.christopherbell.blog;

import dev.christopherbell.blog.model.Post;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class BlogStub {

  public static String BLOG_ID = "5493c406-9a16-45a6-9592-d489bc36cb7a";
  public static String BLOG_AUTHOR = "CBell";
  public static String BLOG_TITLE = "Little Red Miata";
  public static String BLOG_DESCRIPTION = "The little red miata.";
  public static Instant NOW = Instant.now();

  public static Post getPostStub() {
    return Post.builder()
        .id(UUID.fromString(BLOG_ID))
        .author(BLOG_AUTHOR)
        .title(BLOG_TITLE)
        .description(BLOG_DESCRIPTION)
        .createdOn(NOW)
        .build();
  }

  public static List<Post> getPostsStub() {
    return List.of(getPostStub(), getPostStub(), getPostStub());
  }

}
