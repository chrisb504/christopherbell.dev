package dev.christopherbell.post;

import static dev.christopherbell.libs.api.APIVersion.V20250914;

import dev.christopherbell.libs.api.model.Response;
import dev.christopherbell.permission.PermissionService;
import dev.christopherbell.post.model.PostCreateRequest;
import dev.christopherbell.post.model.PostDetail;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing endpoints to create and retrieve posts.
 *
 * <p>All routes are versioned under {@code /api/posts} with API version suffixes
 * from {@link dev.christopherbell.libs.api.APIVersion}. Access is controlled via
 * Spring Security authorities.</p>
 */
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {
  private final PostService postService;
  private final PermissionService permissionService;

  /**
   * Creates a post for the authenticated user.
   *
   * @param request the post creation payload
   * @return HTTP 201 with the created {@link PostDetail}
   * @throws Exception if validation fails or the account cannot be resolved
   */
  @PostMapping(
      value = V20250914 + "/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('USER')")
  public ResponseEntity<Response<PostDetail>> createPost(@RequestBody PostCreateRequest request)
      throws Exception {
    return new ResponseEntity<>(
        Response.<PostDetail>builder()
            .payload(postService.createPost(request))
            .success(true)
            .build(),
        HttpStatus.CREATED);
  }

  /**
   * Retrieves posts authored by the authenticated user.
   *
   * @return HTTP 200 with a list of {@link PostDetail}
   * @throws Exception if the account cannot be resolved
   */
  @GetMapping(value = V20250914 + "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('USER')")
  public ResponseEntity<Response<List<PostDetail>>> getMyPosts() throws Exception {
    return new ResponseEntity<>(
        Response.<List<PostDetail>>builder()
            .payload(postService.getMyPosts())
            .success(true)
            .build(),
        HttpStatus.OK);
  }

  /**
   * Retrieves posts for a specific account id (admin only).
   *
   * @param accountId the account id to filter posts by
   * @return HTTP 200 with a list of {@link PostDetail}
   * @throws Exception if the request is invalid or the account does not exist
   */
  @GetMapping(value = V20250914 + "/account/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("@permissionService.hasAuthority('ADMIN')")
  public ResponseEntity<Response<List<PostDetail>>> getPostsByAccountId(@PathVariable String accountId)
      throws Exception {
    return new ResponseEntity<>(
        Response.<List<PostDetail>>builder()
            .payload(postService.getPostsByAccountId(accountId))
            .success(true)
            .build(),
        HttpStatus.OK);
  }
}

