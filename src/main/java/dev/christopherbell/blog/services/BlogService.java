package dev.christopherbell.blog.services;

import dev.christopherbell.blog.models.blog.BlogResponse;
import dev.christopherbell.blog.models.blog.Post;
import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.configs.Constants;
import dev.christopherbell.blog.configs.properties.BlogProperties;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class BlogService {

  private final BlogProperties blogProperties;

  public BlogResponse getPostById(String id) {
    if (Objects.isNull(id) || id.isBlank()) {
      final var message = new Message("BlogService.getPostById.InvalidId", "Given id is blank, empty or null");
      log.error(message.getDescription());
      final var messages = List.of(message);
      return BlogResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    final var posts = this.blogProperties.getPosts();
    if (Objects.isNull(posts)) {
      final var message = new Message("BlogService.getPosts.NoResults", "No posts found in the config file.");
      log.error(message.getDescription());
      final var messages = List.of(message);
      return BlogResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    Post post = null;
    for (Post blogPost : posts) {
      if (blogPost.getId().equals(Integer.parseInt(id))) {
        post = blogPost;
      }
    }
    return BlogResponse.builder()
        .posts(posts)
        .status(Constants.STATUS_SUCCESS)
        .build();
  }

  public BlogResponse getPosts() {
    final var posts = this.blogProperties.getPosts();
    if (Objects.isNull(posts)) {
      final var message = new Message("BlogService.getPosts.NoResults", "No posts found in the config file.");
      log.error(message.getDescription());
      final var messages = List.of(message);
      return BlogResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    return BlogResponse.builder()
        .posts(posts)
        .status(Constants.STATUS_SUCCESS)
        .build();
  }

  public BlogResponse getTagById(String id) {
    return BlogResponse.builder()
        .status(Constants.STATUS_FAILURE)
        .build();
  }

  public BlogResponse getTags() {
    return BlogResponse.builder()
        .status(Constants.STATUS_FAILURE)
        .build();
  }
}
