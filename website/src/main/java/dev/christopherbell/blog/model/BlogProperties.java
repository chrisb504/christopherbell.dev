package dev.christopherbell.blog.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "blog-properties")
@Data
public class BlogProperties {

  private final List<Post> posts;
}
