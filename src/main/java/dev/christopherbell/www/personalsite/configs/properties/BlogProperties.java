package dev.christopherbell.www.personalsite.configs.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import dev.christopherbell.www.personalsite.models.blog.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blog-properties")
public class BlogProperties {
    private List<Post> posts;
}
