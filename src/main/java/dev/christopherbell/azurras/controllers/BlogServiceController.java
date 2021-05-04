package dev.christopherbell.azurras.controllers;

import dev.christopherbell.azurras.models.blog.BlogRequest;
import dev.christopherbell.azurras.models.blog.BlogResponse;
import dev.christopherbell.azurras.services.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogServiceController {
    private final Logger LOG = LoggerFactory.getLogger(BlogServiceController.class);
    private final BlogService blogService;

    @Autowired
    public BlogServiceController(final BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Takes in a blogRequest to use its data to add a new blog post in the
     * database.
     *
     * @param blogRequest
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse addBlogPost(@RequestBody final BlogRequest blogRequest) {
        if (LOG.isDebugEnabled()) {
            LOG.info("CBBlog: addBlogPost method called with these values: " + blogRequest.toString());
        }
        return this.blogService.addBlogPost(blogRequest);
    }

    /**
     * Takes a blogPostId and uses that Id to delete a post from the database.
     *
     * @param blogPostId
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post/delete/{blogPostId}", method = RequestMethod.POST)
    public BlogResponse deleteBlogPost(@RequestParam final String blogPostId) {
        if (LOG.isDebugEnabled()) {
            LOG.info("CBBlog: deleteBlogPost method called with this value: " + blogPostId);
        }
        return this.blogService.deleteBlogPost(blogPostId);
    }

    /**
     * Takes a blogPostId to retrieve a blog post from the database.
     *
     * @param blogPostId
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post/{blogPostId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogPost(@RequestParam final String blogPostId) {
        if (LOG.isDebugEnabled()) {
            LOG.info("CBBlog: getBlogPost method called with this value: " + blogPostId);
        }
        return this.blogService.getBlogPost(blogPostId);
    }

    /**
     * Returns all blog posts in the database.
     *
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogPosts() {
        if (LOG.isDebugEnabled()) {
            LOG.info("CBBlog: getBlogPosts method called");
        }
        return this.blogService.getBlogPosts();
    }

    /**
     * Takes in a blogTagId to return all blog post with that tag from the database.
     *
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/tag/{blogTagId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogTag(@RequestParam final String blogTagId) {
        if(LOG.isDebugEnabled()) {
            LOG.info("CBBlog: getBlogTag method called with this value: " + blogTagId);
        }
        return this.blogService.getBlogTag(blogTagId);
    }

    /**
     * Returns all tags in the database.
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogTags() {
        if(LOG.isDebugEnabled()) {
            LOG.info("CBBlog: getBlogTags method called");
        }
        return this.blogService.getBlogTags();
    }
}
