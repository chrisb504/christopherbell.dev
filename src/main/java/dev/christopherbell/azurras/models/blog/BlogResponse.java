package dev.christopherbell.azurras.models.blog;

import lombok.Getter;
import java.util.List;

import dev.christopherbell.azurras.models.global.Message;
import dev.christopherbell.azurras.models.global.Response;

@Getter
public class BlogResponse extends Response {
    private List<BlogPost> blogPosts;
    private List<String> blogTags;

    public BlogResponse(List<BlogPost> blogPosts,
            List<String> blogTags,
            List<Message> messages,
            String status) {
        super(messages, status);
        this.blogPosts = blogPosts;
        this.blogTags = blogTags;
    }
}
