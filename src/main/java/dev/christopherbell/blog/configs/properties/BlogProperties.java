package dev.christopherbell.blog.configs.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import dev.christopherbell.blog.models.blog.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blog-properties")
public class BlogProperties {
    private List<Post> posts;
}
