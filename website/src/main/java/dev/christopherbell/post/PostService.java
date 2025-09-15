package dev.christopherbell.post;

import dev.christopherbell.account.AccountRepository;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.permission.PermissionService;
import dev.christopherbell.post.model.Post;
import dev.christopherbell.post.model.PostCreateRequest;
import dev.christopherbell.post.model.PostDetail;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Application service for creating and retrieving tweet‑like posts.
 *
 * <p>Enforces input validation, ensures ownership via the authenticated
 * account, and delegates persistence to {@link PostRepository}.</p>
 */
@RequiredArgsConstructor
@Service
public class PostService {
  private final PostRepository postRepository;
  private final AccountRepository accountRepository;
  private final PostMapper postMapper;

  private static final int MAX_TEXT_LENGTH = 280;

  /**
   * Creates a new post for the currently authenticated account.
   *
   * @param request input containing the post text (required, ≤ 280 chars)
   * @return created post as a {@link PostDetail}
   * @throws InvalidRequestException if text is null/blank or exceeds limits
   * @throws ResourceNotFoundException if the current account cannot be found
   */
  public PostDetail createPost(PostCreateRequest request)
      throws InvalidRequestException, ResourceNotFoundException {
    if (request == null || request.text() == null || request.text().isBlank()) {
      throw new InvalidRequestException("Post text cannot be null or blank.");
    }

    var text = request.text().trim();
    if (text.length() > MAX_TEXT_LENGTH) {
      throw new InvalidRequestException("Post text exceeds 280 characters.");
    }
    String selfId = getSelfId();
    var account = accountRepository
        .findById(selfId)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with id %s not found.", selfId)));
    var now = Instant.now();
    var post = Post.builder()
        .id(UUID.randomUUID().toString())
        .accountId(account.getId())
        .text(text)
        .createdOn(now)
        .lastUpdatedOn(now)
        .build();

    var saved = postRepository.save(post);
    return postMapper.toDetail(saved);
  }

  /**
   * Lists posts authored by the current account (newest first).
   *
   * @return list of post details for the caller
   * @throws ResourceNotFoundException if the current account cannot be found
   */
  public List<PostDetail> getMyPosts() throws ResourceNotFoundException {
    String selfId = getSelfId();
    // Ensure account exists (defensive, consistent with create)
    accountRepository
        .findById(selfId)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with id %s not found.", selfId)));

    return postRepository.findByAccountIdOrderByCreatedOnDesc(selfId)
        .stream().map(postMapper::toDetail).toList();
  }

  /**
   * Lists posts for a given account id (newest first).
   *
   * @param accountId the account id to filter by (required)
   * @return list of post details for the account
   * @throws InvalidRequestException if the id is null or blank
   * @throws ResourceNotFoundException if the account does not exist
   */
  public List<PostDetail> getPostsByAccountId(String accountId)
      throws InvalidRequestException, ResourceNotFoundException {
    if (accountId == null || accountId.isBlank()) {
      throw new InvalidRequestException("Account id cannot be null or blank.");
    }
    accountRepository
        .findById(accountId)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with id %s not found.", accountId)));

    return postRepository.findByAccountIdOrderByCreatedOnDesc(accountId)
        .stream().map(postMapper::toDetail).toList();
  }

  /**
   * Resolves the id of the authenticated account.
   * Separated for testability.
   */
  String getSelfId() {
    return PermissionService.getSelf();
  }
}
