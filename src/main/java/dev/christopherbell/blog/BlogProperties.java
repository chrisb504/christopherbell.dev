package dev.christopherbell.blog;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "blog-properties")
public class BlogProperties {

  private final List<Post> posts;
}
