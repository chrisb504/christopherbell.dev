package dev.christopherbell.blog.configs.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import dev.christopherbell.blog.models.photogallery.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "image-properties")
public class ImageProperties {
    private List<Image> images;
}
