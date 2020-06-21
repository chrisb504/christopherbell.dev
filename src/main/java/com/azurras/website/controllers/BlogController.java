package com.azurras.website.controllers;

import com.azurras.website.models.blog.BlogRequest;
import com.azurras.website.models.blog.BlogResponse;
import com.azurras.website.services.BlogService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
    private final Log LOG = LogFactory.getLog(BlogController.class);
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Takes in a blogRequest to use its data to add a new blog post in the 
     * database.
     * @param blogRequest
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse addBlogPost(@RequestBody BlogRequest blogRequest) {
        return this.blogService.addBlogPost(blogRequest);
    }

    /**
     * Takes a blogPostId and uses that Id to delete a post from
     * the database.
     * @param blogPostId
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post/delete/{blogPostId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse deleteBlogPost(String blogPostId) {
        return this.blogService.deleteBlogPost(blogPostId);
    }

    /**
     * Returns the html for the blog home page.
     * @return blog
     */
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String getBlogPage() {
        return "blog";
    }

    /**
     * Returns the html for the blog add page.
     * @return blogAdd
     */
    @RequestMapping(value = "/blog/add", method = RequestMethod.GET)
    public String getBlogAddPage() {
        return "blogAdd";
    }

    /**
     * Returns the html for the blog delete page.
     * @return blogDelete
     */
    @RequestMapping(value = "/blog/delete", method = RequestMethod.GET)
    public String getBlogDeletePage() {
        return "blogDelete";
    }

    /**
     * Takes a blogPostId to retreive a blog post from the database.
     * @param blogPostId
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post/{blogPostId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogPost(String blogPostId) {
        return this.blogService.getBlogPost(blogPostId);
    }

    /**
     * Returns all blog posts in the database.
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/post", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogPosts() {
        return this.blogService.getBlogPosts();
    }

    /**
     * Takes in a blogTagId to return all blog post with
     * that tag from the database.
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/tag/{blogTagId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogTag(String blogTagId) {
        return this.blogService.getBlogTag(blogTagId);
    }

    /**
     * Returns all tags in the database.
     * @return BlogResponse
     */
    @ResponseBody
    @RequestMapping(value = "/blog/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlogResponse getBlogTags() {
        return this.blogService.getBlogTags();
    }

}