package dev.christopherbell.www.personalsite.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.christopherbell.www.personalsite.configs.Constants;
import dev.christopherbell.www.personalsite.configs.properties.BlogProperties;
import dev.christopherbell.www.personalsite.models.blog.BlogResponse;
import dev.christopherbell.www.personalsite.models.blog.Post;
import dev.christopherbell.www.personalsite.models.global.Message;

import java.util.Arrays;
import java.util.Objects;

@Service
public class BlogService {
    private final Logger LOG = LoggerFactory.getLogger(BlogService.class);
    private final BlogProperties blogProperties;

    @Autowired
    public BlogService(BlogProperties blogProperties) {
        this.blogProperties = blogProperties;
    }

    public BlogResponse getPostById(String id) {
        if (Objects.isNull(id) || id.isBlank()) {
            final var message = new Message("BlogService.getPostById.InvalidId", "Given id is blank, empty or null");
            LOG.error(message.getDescription());
            final var messages = Arrays.asList(message);
            return new BlogResponse(null, messages, Constants.STATUS_FAILURE);
        }
        final var posts = this.blogProperties.getPosts();
        if (Objects.isNull(posts)) {
            final var message = new Message("BlogService.getPosts.NoResults", "No posts found in the config file.");
            LOG.error(message.getDescription());
            final var messages = Arrays.asList(message);
            return new BlogResponse(null, messages, Constants.STATUS_FAILURE);
        }
        Post post = null;
        for (Post blogPost : posts) {
            if (blogPost.getId().equals(Integer.parseInt(id))) {
                post = blogPost;
            }
        }
        return new BlogResponse(post, null, null);
    }

    public BlogResponse getPosts() {
        final var posts = this.blogProperties.getPosts();
        if (Objects.isNull(posts)) {
            final var message = new Message("BlogService.getPosts.NoResults", "No posts found in the config file.");
            LOG.error(message.getDescription());
            final var messages = Arrays.asList(message);
            return new BlogResponse(null, null, messages, Constants.STATUS_FAILURE);
        }
        return new BlogResponse(posts, null, null, null);
    }

    public BlogResponse getTagById(String id) {
        return new BlogResponse(null, null, null, null);
    }

    public BlogResponse getTags() {
        return new BlogResponse(null, null, null, null);
    }
}
