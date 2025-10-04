package dev.christopherbell.blog;

import dev.christopherbell.blog.model.BlogResponse;
import dev.christopherbell.libs.api.model.Response;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.permission.PermissionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for blog content under {@code /api/blog}.
 *
 * <p>Endpoints return a {@link Response} envelope containing a {@link BlogResponse} payload.</p>
 */
@AllArgsConstructor
@RequestMapping("/api/blog")
@RestController
public class BlogController {
  private final BlogService blogService;
  private final PermissionService permissionService;

  /**
   * Retrieves a single post by its ID.
   *
   * @param id the post identifier
   * @return HTTP 200 with a {@link BlogResponse} containing the post
   */
  @GetMapping(value = "/v1/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<BlogResponse>> getBlogPostById(HttpServletRequest request, @PathVariable String id)
      throws InvalidRequestException {
    return new ResponseEntity<>(
        Response.<BlogResponse>builder()
            .payload(blogService.getPostById(id))
            .success(true)
            .build(), HttpStatus.OK);
  }

  /**
   * Lists all posts.
   *
   * @return HTTP 200 with a {@link BlogResponse} containing all posts
   */
  @GetMapping(value = "/v1/posts", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<BlogResponse>> getBlogPosts(HttpServletRequest request) {
    return new ResponseEntity<>(
        Response.<BlogResponse>builder()
            .payload(blogService.getPosts())
            .success(true)
            .build(), HttpStatus.OK);
  }
}
