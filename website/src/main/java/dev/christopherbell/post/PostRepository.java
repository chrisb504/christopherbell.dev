package dev.christopherbell.post;

import dev.christopherbell.post.model.Post;
import java.util.List;
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
}

