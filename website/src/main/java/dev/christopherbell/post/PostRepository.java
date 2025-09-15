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
  List<Post> findByAccountIdOrderByCreatedOnDesc(String accountId, Pageable pageable);
  List<Post> findByAccountIdAndCreatedOnLessThanOrderByCreatedOnDesc(String accountId, Instant before, Pageable pageable);

  /**
   * Retrieves a page of posts across all accounts ordered by created time descending.
   */
  Page<Post> findAll(Pageable pageable);

  /**
   * Retrieves posts created before the given instant, newest first.
   */
  List<Post> findByCreatedOnLessThanOrderByCreatedOnDesc(Instant before, Pageable pageable);
}

