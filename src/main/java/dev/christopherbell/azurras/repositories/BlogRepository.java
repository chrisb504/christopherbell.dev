package dev.christopherbell.azurras.repositories;

import dev.christopherbell.azurras.models.blog.BlogPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<BlogPost, Integer> {
    BlogPost findByAuthor(String author);
    BlogPost findById(int id);
    BlogPost findByTags(String tag);
    BlogPost findByTitle(String title);
}
