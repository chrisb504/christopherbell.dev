package dev.christopherbell.azurras.configs.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import dev.christopherbell.azurras.models.photogallery.Image;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "image-properties")
public class ImageProperties {
    private List<Image> images;
}
