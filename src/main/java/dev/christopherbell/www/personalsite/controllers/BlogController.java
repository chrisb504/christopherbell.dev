package dev.christopherbell.www.personalsite.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dev.christopherbell.www.personalsite.models.global.Response;
import dev.christopherbell.www.personalsite.services.BlogService;

@RestController
public class BlogController {
    private final Logger LOG = LoggerFactory.getLogger(BlogController.class);
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Takes a id to retrieve a blog post from the database.
     *
     * @param id
     * @return BlogResponse
     */
    @GetMapping(value = "/api/blog/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getBlogPost(HttpServletRequest request, @PathVariable String id) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to get blog post with the following id: {}", id);
        }
        var response = this.blogService.getPostById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all blog posts in the database.
     *
     * @return BlogResponse
     */
    @GetMapping(value = "/api/blog/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getBlogPosts(HttpServletRequest request) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to get all blog post.");
        }
        var response = this.blogService.getPosts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Takes in a id to return all blog post with that tag from the database.
     *
     * @param id
     * @return BlogResponse
     */
    @GetMapping(value = "/api/blog/posts/tags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getBlogTag(HttpServletRequest request, @PathVariable String id) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to get blog post tag with the following id: {}", id);
        }
        var response = this.blogService.getTagById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns all tags in the database.
     * 
     * @return BlogResponse
     */
    @GetMapping(value = "/api/blog/posts/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getBlogTags(HttpServletRequest request) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to return all blog post tags");
        }
        var response = this.blogService.getTags();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
