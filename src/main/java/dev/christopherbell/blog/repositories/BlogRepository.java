package dev.christopherbell.blog.repositories;

import dev.christopherbell.blog.models.blog.Post;

//@Repository
public interface BlogRepository {// extends CrudRepository<BlogPost, Integer> {
    Post findByAuthor(String author);

    Post findById(int id);

    Post findByTags(String tag);

    Post findByTitle(String title);
}
