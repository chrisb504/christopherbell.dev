package com.azurras.website.controllers;

import com.azurras.website.models.blog.BlogResponse;
import com.azurras.website.services.BlogService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {
    private final Log LOG = LogFactory.getLog(BlogController.class);
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/blog/add", method = RequestMethod.POST)
    public BlogResponse addBlogPost() {
        return new BlogResponse();
    }

    @RequestMapping(value = "/blog/delete/{blogPostId}", method = RequestMethod.POST)
    public BlogResponse deleteBlogPost(String blogPostId) {
        return new BlogResponse();
    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String getBlogPage() {
        return "blog";
    }

    @RequestMapping(value = "/blog/{blogPostId}", method = RequestMethod.GET)
    public BlogResponse getBlogPost(String blogPostId) {
        return new BlogResponse();
    }

}