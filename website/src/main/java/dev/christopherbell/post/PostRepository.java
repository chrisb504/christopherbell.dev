package dev.christopherbell.post;

import dev.christopherbell.post.model.Post;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Mongo repository for {@link dev.christopherbell.post.model.Post} entities.
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
  /**
   * Retrieves posts for a given account, newest first.
   *
   * @param accountId the owning account id
   * @return list of posts ordered by {@code createdOn} descending
   */
  List<Post> findByAccountIdOrderByCreatedOnDesc(String accountId);
  /**
   * Retrieves posts for a given account, newest first, with pagination.
   *
   * @param accountId the owning account id
   * @param pageable  page request (size, sort)
   * @return a page slice of posts ordered by {@code createdOn} descending
   */
  List<Post> findByAccountIdOrderByCreatedOnDesc(String accountId, Pageable pageable);

  /**
   * Retrieves posts for a given account created before the given time, newest first, with pagination.
   *
   * @param accountId the owning account id
   * @param before    exclusive upper bound for {@code createdOn}
   * @param pageable  page request (size, sort)
   * @return a page slice of older posts ordered by {@code createdOn} descending
   */
  List<Post> findByAccountIdAndCreatedOnLessThanOrderByCreatedOnDesc(String accountId, Instant before, Pageable pageable);

  /**
   * Retrieves a page of posts across all accounts ordered by created time descending.
   */
  Page<Post> findAll(Pageable pageable);

  /**
   * Retrieves posts created before the given instant, newest first.
   */
  List<Post> findByCreatedOnLessThanOrderByCreatedOnDesc(Instant before, Pageable pageable);

  /** All posts in a thread (includes root) ordered oldest-first (by createdOn). */
  List<Post> findByRootIdOrderByCreatedOnAsc(String rootId);

  /** Count of direct replies to a given post (parentId equals the post id). */
  long countByParentId(String parentId);

  /** Deletes posts whose expiration timestamp is at or before the provided instant. */
  long deleteByExpiresOnLessThanEqual(Instant cutoff);

  /** Finds posts that have not been assigned an expiration timestamp yet. */
  List<Post> findByExpiresOnIsNull();
}
