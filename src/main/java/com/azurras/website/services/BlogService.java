package com.azurras.website.services;

import com.azurras.website.models.blog.BlogRequest;
import com.azurras.website.models.blog.BlogResponse;
import com.azurras.website.utils.Constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final Log LOG = LogFactory.getLog(BlogService.class);

    public BlogService() {
    }

    public BlogResponse addBlogPost(BlogRequest blogRequest) {
        if (blogRequest == null) {
            LOG.error(Constants.BLOG_NULL_REQUEST);
            this.getErrorBlogResponse(Constants.BLOG_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new BlogResponse();
    }

    public BlogResponse deleteBlogPost(String blogPostId) {
        if (blogPostId == null || blogPostId.isEmpty()) {
            LOG.error(Constants.BLOG_NULL_REQUEST);
            this.getErrorBlogResponse(Constants.BLOG_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new BlogResponse();
    }

    public BlogResponse getBlogPost(String blogPostId) {
        if (blogPostId == null || blogPostId.isEmpty()) {
            LOG.error(Constants.BLOG_NULL_REQUEST);
            this.getErrorBlogResponse(Constants.BLOG_NULL_REQUEST, String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return new BlogResponse();
    }

    public BlogResponse getBlogPosts() {
        return new BlogResponse();
    }

    public BlogResponse getBlogTag() {
        return new BlogResponse();
    }

    public BlogResponse getBlogTags() {
        return new BlogResponse();
    }

    private BlogResponse getErrorBlogResponse(String message, String status) {
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setMessage(message);
        blogResponse.setStatus(status);
        return blogResponse;
    }
}