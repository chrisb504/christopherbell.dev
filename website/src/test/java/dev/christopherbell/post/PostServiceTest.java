package dev.christopherbell.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import dev.christopherbell.account.AccountRepository;
import dev.christopherbell.account.AccountServiceStub;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.post.model.Post;
import dev.christopherbell.post.model.PostCreateRequest;
import dev.christopherbell.post.model.PostDetail;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
  @Mock private PostRepository postRepository;
  @Mock private AccountRepository accountRepository;
  @Mock private PostMapper postMapper;
  @InjectMocks private PostService postService;

  @Test
  @DisplayName("Create: valid -> saves and returns detail")
  public void testCreatePost_whenValid_SavesAndReturnsDetail() throws Exception {
    var existing = AccountServiceStub.getAccountWhenExistsStub();
    var request = PostCreateRequest.builder().text("hello world").build();

    var service = spy(postService);
    doReturn(existing.getId()).when(service).getSelfId();
    when(accountRepository.findById(eq(existing.getId()))).thenReturn(Optional.of(existing));

    var post = Post.builder()
        .id("p1").accountId(existing.getId()).text("hello world")
        .createdOn(Instant.now()).lastUpdatedOn(Instant.now())
        .build();
    when(postRepository.save(org.mockito.ArgumentMatchers.any(Post.class))).thenReturn(post);
    var detail = PostDetail.builder().id("p1").accountId(existing.getId()).text("hello world").build();
    when(postMapper.toDetail(eq(post))).thenReturn(detail);

    var result = service.createPost(request);

    assertNotNull(result);
    assertEquals("p1", result.id());
    verify(accountRepository).findById(eq(existing.getId()));
    verify(postRepository).save(org.mockito.ArgumentMatchers.any(Post.class));
    verify(postMapper).toDetail(eq(post));
    verifyNoMoreInteractions(accountRepository, postRepository, postMapper);
  }

  @Test
  @DisplayName("Create: blank -> 400 InvalidRequestException")
  public void testCreatePost_whenBlank_Throws400() {
    var request = PostCreateRequest.builder().text("   ").build();
    assertThrows(InvalidRequestException.class, () -> postService.createPost(request));
  }

  @Test
  @DisplayName("Create: too long -> 400 InvalidRequestException")
  public void testCreatePost_whenTooLong_Throws400() {
    var longText = "a".repeat(281);
    var request = PostCreateRequest.builder().text(longText).build();
    assertThrows(InvalidRequestException.class, () -> postService.createPost(request));
  }

  @Test
  @DisplayName("Create: account not found -> 404")
  public void testCreatePost_whenAccountMissing_Throws404() {
    var request = PostCreateRequest.builder().text("hello").build();
    var service = spy(postService);
    doReturn("missing").when(service).getSelfId();
    when(accountRepository.findById(eq("missing"))).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> service.createPost(request));

    verify(accountRepository).findById(eq("missing"));
    verifyNoMoreInteractions(accountRepository);
  }

  @Test
  @DisplayName("GetMy: returns mapped list")
  public void testGetMyPosts_whenSome_ReturnsList() throws Exception {
    var existing = AccountServiceStub.getAccountWhenExistsStub();
    var service = spy(postService);
    doReturn(existing.getId()).when(service).getSelfId();
    when(accountRepository.findById(eq(existing.getId()))).thenReturn(Optional.of(existing));

    var p1 = Post.builder().id("p1").accountId(existing.getId()).text("a").build();
    var d1 = PostDetail.builder().id("p1").text("a").build();
    when(postRepository.findByAccountIdOrderByCreatedOnDesc(eq(existing.getId())))
        .thenReturn(List.of(p1));
    when(postMapper.toDetail(eq(p1))).thenReturn(d1);

    var list = service.getMyPosts();
    assertEquals(1, list.size());
    assertEquals("p1", list.get(0).id());
  }

  @Test
  @DisplayName("GetMy: account not found -> 404")
  public void testGetMyPosts_whenAccountMissing_Throws404() {
    var service = spy(postService);
    doReturn("missing").when(service).getSelfId();
    when(accountRepository.findById(eq("missing"))).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, service::getMyPosts);
  }

  @Test
  @DisplayName("GetByAccount: invalid id -> 400")
  public void testGetPostsByAccount_whenInvalid_Throws400() {
    assertThrows(InvalidRequestException.class, () -> postService.getPostsByAccountId(null));
    assertThrows(InvalidRequestException.class, () -> postService.getPostsByAccountId("  "));
  }

  @Test
  @DisplayName("GetByAccount: not found -> 404")
  public void testGetPostsByAccount_whenMissing_Throws404() {
    when(accountRepository.findById(eq("missing"))).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> postService.getPostsByAccountId("missing"));
  }

  @Test
  @DisplayName("GetByAccount: returns mapped list")
  public void testGetPostsByAccount_whenSome_ReturnsList() throws Exception {
    var existing = AccountServiceStub.getAccountWhenExistsStub();
    var p1 = Post.builder().id("p1").accountId(existing.getId()).text("t").build();
    var d1 = PostDetail.builder().id("p1").text("t").build();

    when(accountRepository.findById(eq(existing.getId()))).thenReturn(Optional.of(existing));
    when(postRepository.findByAccountIdOrderByCreatedOnDesc(eq(existing.getId())))
        .thenReturn(List.of(p1));
    when(postMapper.toDetail(eq(p1))).thenReturn(d1);

    var list = postService.getPostsByAccountId(existing.getId());
    assertEquals(1, list.size());
    assertEquals("p1", list.get(0).id());

    verify(accountRepository).findById(eq(existing.getId()));
    verify(postRepository).findByAccountIdOrderByCreatedOnDesc(eq(existing.getId()));
    verify(postMapper).toDetail(eq(p1));
    verifyNoMoreInteractions(accountRepository, postRepository, postMapper);
  }
}

