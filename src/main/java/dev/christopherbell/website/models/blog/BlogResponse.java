package dev.christopherbell.website.models.blog;

import lombok.Getter;
import java.util.List;

import dev.christopherbell.website.models.global.Message;
import dev.christopherbell.website.models.global.Response;

@Getter
public class BlogResponse extends Response {
    private List<Post> posts;
    private List<String> tags;

    public BlogResponse(List<Post> posts,
            List<String> tags,
            List<Message> messages,
            String status) {
        super(messages, status);
        this.posts = posts;
        this.tags = tags;
    }
}
