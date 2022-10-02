package dev.christopherbell.website.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.christopherbell.website.configs.Constants;
import dev.christopherbell.website.configs.properties.BlogProperties;
import dev.christopherbell.website.models.blog.BlogRequest;
import dev.christopherbell.website.models.blog.BlogResponse;
import dev.christopherbell.website.utils.BlogUtil;

import java.util.Objects;

@Service
public class BlogService {
    private final Logger LOG = LoggerFactory.getLogger(BlogService.class);
    private final BlogProperties blogProperties;

    @Autowired
    public BlogService(BlogProperties blogProperties) {
        this.blogProperties = blogProperties;
    }


    public BlogResponse getPostById(final String blogPostId) {
        if (Objects.isNull(blogPostId) || blogPostId.isEmpty()) {
            LOG.error(Constants.ERROR_NULL_REQUEST);
            return BlogUtil.getBaseBlogResponse(Constants.ERROR_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        final var blogResponse = BlogUtil.getBaseBlogResponse(Constants.STATUS_SUCCESS,
                String.valueOf(HttpStatus.OK));

        return new BlogResponse(null, null, null, null);
    }

    public BlogResponse getPosts() {
        var posts = this.blogProperties.getPosts();
        return new BlogResponse(posts, null, null, null);
    }

    public BlogResponse getTagById(final String blogPostTag) {
        return new BlogResponse(null, null, null, null);
    }

    public BlogResponse getTags() {
        // final var rawBlogPosts = this.blogRepository.findAll();
        // final var blogTags = new ArrayList<String>();
        //
        // for (final BlogPost blogPost : rawBlogPosts) {
        // blogTags.add(blogPost.getTags());
        // }
        // final var blogResponse =
        // BlogUtil.getBaseBlogResponse(Constants.STATUS_SUCCESS,
        // String.valueOf(HttpStatus.OK));
        // blogResponse.setBlogTagPayLoad(blogTags);
        // return blogResponse;

        return new BlogResponse(null, null, null, null);
    }
}
