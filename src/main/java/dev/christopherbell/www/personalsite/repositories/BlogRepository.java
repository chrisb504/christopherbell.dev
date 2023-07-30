package dev.christopherbell.www.personalsite.repositories;

import dev.christopherbell.www.personalsite.models.blog.Post;

//@Repository
public interface BlogRepository {// extends CrudRepository<BlogPost, Integer> {
    Post findByAuthor(String author);

    Post findById(int id);

    Post findByTags(String tag);

    Post findByTitle(String title);
}
