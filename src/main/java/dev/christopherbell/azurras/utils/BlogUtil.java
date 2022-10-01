package dev.christopherbell.azurras.utils;

import dev.christopherbell.azurras.models.blog.BlogPost;
import dev.christopherbell.azurras.models.blog.BlogRequest;
import dev.christopherbell.azurras.models.blog.BlogResponse;
import dev.christopherbell.azurras.models.global.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class BlogUtil {
    private final Logger LOG = LoggerFactory.getLogger(BlogUtil.class);

    private BlogUtil() {
    }

    public static BlogResponse getBaseBlogResponse(final String message, final String status) {
        var newMessage = new Message(message, "");
        // return new BlogResponse(null, null, newMessage, status);
        return new BlogResponse(null, null, null, null);
    }

    public static BlogPost getBlogPost(final BlogRequest blogRequest) {
        BlogPost blogPost = null;
        if (Objects.nonNull(blogRequest.getAuthor()) && Objects.nonNull(blogRequest.getContentText())
                && Objects.nonNull(blogRequest.getDescription()) && Objects.nonNull(blogRequest.getImagePath())
                && Objects.nonNull(blogRequest.getTitle())) {
            var author = blogRequest.getAuthor();
            var contentText = blogRequest.getContentText();
            var description = blogRequest.getDescription();
            var imagePath = blogRequest.getImagePath();
            var tags = blogRequest.getTags();
            var title = blogRequest.getTitle();
            blogPost = new BlogPost(author, contentText, description, imagePath, tags, title);
        }

        return blogPost;
    }
}
