package dev.christopherbell.post;

import dev.christopherbell.post.model.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
  List<Post> findByAccountIdOrderByCreatedOnDesc(String accountId);
}

