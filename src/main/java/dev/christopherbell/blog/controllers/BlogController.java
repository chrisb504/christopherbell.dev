package dev.christopherbell.blog.controllers;

import dev.christopherbell.blog.models.global.Response;
import dev.christopherbell.blog.services.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BlogController {

  private final BlogService blogService;


  /**
   * Takes an id to retrieve a blog post from the database.
   *
   * @param id
   * @return BlogResponse
   */
  @GetMapping(value = "/api/blog/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getBlogPost(HttpServletRequest request, @PathVariable String id) {
    var response = this.blogService.getPostById(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Returns all blog posts in the database.
   *
   * @return BlogResponse
   */
  @GetMapping(value = "/api/blog/posts", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getBlogPosts(HttpServletRequest request) {
    var response = this.blogService.getPosts();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Takes in a id to return all blog post with that tag from the database.
   *
   * @param id
   * @return BlogResponse
   */
  @GetMapping(value = "/api/blog/posts/tags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getBlogTag(HttpServletRequest request, @PathVariable String id) {
    var response = this.blogService.getTagById(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Returns all tags in the database.
   *
   * @return BlogResponse
   */
  @GetMapping(value = "/api/blog/posts/tags", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getBlogTags(HttpServletRequest request) {
    var response = this.blogService.getTags();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
