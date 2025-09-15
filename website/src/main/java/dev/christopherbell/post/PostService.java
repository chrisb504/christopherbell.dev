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

@RequiredArgsConstructor
@Service
public class PostService {
  private final PostRepository postRepository;
  private final AccountRepository accountRepository;
  private final PostMapper postMapper;

  private static final int MAX_TEXT_LENGTH = 280;

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

  public List<PostDetail> getMyPosts() throws ResourceNotFoundException {
    String selfId = getSelfId();
    // Ensure account exists (defensive, consistent with create)
    accountRepository
        .findById(selfId)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with id %s not found.", selfId)));

    return postRepository.findByAccountIdOrderByCreatedOnDesc(selfId)
        .stream().map(postMapper::toDetail).toList();
  }

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

  // Visible for testing
  String getSelfId() {
    return PermissionService.getSelf();
  }
}
