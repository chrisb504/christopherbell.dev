package dev.christopherbell.blog.configs;

import dev.christopherbell.blog.models.photogallery.Image;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "image-properties")
public class ImageProperties {

  private final List<Image> images;
}
