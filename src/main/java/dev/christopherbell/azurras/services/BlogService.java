package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.models.blog.BlogPost;
import dev.christopherbell.azurras.models.blog.BlogRequest;
import dev.christopherbell.azurras.models.blog.BlogResponse;
import dev.christopherbell.azurras.repositories.BlogRepository;
import dev.christopherbell.azurras.utils.BlogUtil;
import dev.christopherbell.azurras.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class BlogService {
    private final Logger LOG = LoggerFactory.getLogger(BlogService.class);
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
        //this.blogRepository.save(blogPost);

        return BlogUtil.getBaseBlogResponse(Constants.POST_ADDED_SUCCESS, String.valueOf(HttpStatus.OK));
    }

    public BlogResponse deleteBlogPost(final String blogPostId) {
        if (Objects.isNull(blogPostId) || blogPostId.isEmpty()) {
            LOG.error(Constants.ERROR_NULL_REQUEST);
            return BlogUtil.getBaseBlogResponse(Constants.ERROR_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        //this.blogRepository.deleteById((Integer.parseInt(blogPostId)));

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
        //blogResponse.getBlogPostPayload().add(blogPost.get());

        return new BlogResponse();
    }

    public BlogResponse getBlogPosts() {
//        final var rawBlogPosts = this.blogRepository.findAll();
//        final var blogPosts = new ArrayList<BlogPost>();
//
//        for (final BlogPost blogPost : rawBlogPosts) {
//            blogPosts.add(blogPost);
//        }
//        final var blogResponse = BlogUtil.getBaseBlogResponse(Constants.STATUS_SUCCESS,
//                String.valueOf(HttpStatus.OK));
//        blogResponse.setBlogPostPayLoad(blogPosts);

        return new BlogResponse();//blogResponse;
    }

    public BlogResponse getBlogTag(final String blogPostTag) {
        return new BlogResponse();
    }

    public BlogResponse getBlogTags() {
//        final var rawBlogPosts = this.blogRepository.findAll();
//        final var blogTags = new ArrayList<String>();
//
//        for (final BlogPost blogPost : rawBlogPosts) {
//            blogTags.add(blogPost.getTags());
//        }
//        final var blogResponse = BlogUtil.getBaseBlogResponse(Constants.STATUS_SUCCESS,
//                String.valueOf(HttpStatus.OK));
//        blogResponse.setBlogTagPayLoad(blogTags);
//        return blogResponse;

        return new BlogResponse();
    }
}
