package dev.christopherbell.blog.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties holding static blog content.
 *
 * <p>Bound from application configuration using prefix {@code blog-properties}.</p>
 */
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "blog-properties")
@Data
public class BlogProperties {

  private final List<Post> posts;
}
