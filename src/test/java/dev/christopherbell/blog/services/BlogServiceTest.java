package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.BlogProperties;
import dev.christopherbell.libs.common.api.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BlogServiceTest {
  private BlogService blogService;
  @Autowired
  private BlogProperties blogProperties;

  @BeforeEach
  public void init() {
    blogService = new BlogService(blogProperties);
  }

  @Test
  public void testGetPostById_success() throws InvalidRequestException {
    var id = 0;

    var post = blogService.getPostById(String.valueOf(id));
    Assertions.assertEquals(post.getPosts().getFirst().getId(),
        blogProperties.getPosts().getFirst().getId());
  }

  @Test
  public void testGetPostById_failure_nullId() {
    Assertions.assertThrows(InvalidRequestException.class, () -> {
      blogService.getPostById(null);
    });
  }

  @Test
  public void testGetPostById_failure_blankId() {
    Assertions.assertThrows(InvalidRequestException.class, () -> {
      blogService.getPostById("");
    });
  }

  @Test
  public void testGetPostById_failure_doesNotExist() {
    Assertions.assertThrows(InvalidRequestException.class, () -> {
      blogService.getPostById("-1");
    });
  }

  @Test
  public void testGetPosts_success() {
    var posts = blogService.getPosts();

    Assertions.assertEquals(posts.getPosts().size(),
        blogProperties.getPosts().size());
  }

}
