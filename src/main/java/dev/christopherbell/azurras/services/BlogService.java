package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.models.blog.BlogPost;
import dev.christopherbell.azurras.models.blog.BlogRequest;
import dev.christopherbell.azurras.models.blog.BlogResponse;
import dev.christopherbell.azurras.repositories.BlogRepository;
import dev.christopherbell.azurras.utils.BlogUtil;
import dev.christopherbell.azurras.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class BlogService {
    private final Log LOG = LogFactory.getLog(BlogService.class);
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(final BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponse addBlogPost(final BlogRequest blogRequest) {
        if (Objects.isNull(blogRequest)) {
            LOG.error(Constants.ERROR_NULL_REQUEST);
            return BlogUtil.getBaseBlogResponse(Constants.ERROR_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        final var blogPost = BlogUtil.getBlogPost(blogRequest);
        this.blogRepository.save(blogPost);

        return BlogUtil.getBaseBlogResponse(Constants.POST_ADDED_SUCCESS, String.valueOf(HttpStatus.OK));
    }

    public BlogResponse deleteBlogPost(final String blogPostId) {
        if (Objects.isNull(blogPostId) || blogPostId.isEmpty()) {
            LOG.error(Constants.ERROR_NULL_REQUEST);
            return BlogUtil.getBaseBlogResponse(Constants.ERROR_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        this.blogRepository.deleteById((Integer.parseInt(blogPostId)));

        return BlogUtil.getBaseBlogResponse(Constants.POST_DELETED_SUCCESS, String.valueOf(HttpStatus.OK));
    }

    public BlogResponse getBlogPost(final String blogPostId) {
        if (Objects.isNull(blogPostId) || blogPostId.isEmpty()) {
            LOG.error(Constants.ERROR_NULL_REQUEST);
            return BlogUtil.getBaseBlogResponse(Constants.ERROR_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        final var blogPost = this.blogRepository.findById(Integer.valueOf(blogPostId));
        final var blogResponse = BlogUtil.getBaseBlogResponse(Constants.STATUS_SUCCESS,
                String.valueOf(HttpStatus.OK));
        blogResponse.getBlogPostPayload().add(blogPost.get());

        return blogResponse;
    }

    public BlogResponse getBlogPosts() {
        final var rawBlogPosts = this.blogRepository.findAll();
        final var blogPosts = new ArrayList<BlogPost>();

        for (final BlogPost blogPost : rawBlogPosts) {
            blogPosts.add(blogPost);
        }
        final var blogResponse = BlogUtil.getBaseBlogResponse(Constants.STATUS_SUCCESS,
                String.valueOf(HttpStatus.OK));
        blogResponse.setBlogPostPayLoad(blogPosts);

        return blogResponse;
    }

    public BlogResponse getBlogTag(final String blogPostTag) {
        return new BlogResponse();
    }

    public BlogResponse getBlogTags() {
        return new BlogResponse();
    }
}
