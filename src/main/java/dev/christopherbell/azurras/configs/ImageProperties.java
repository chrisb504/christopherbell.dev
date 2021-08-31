package dev.christopherbell.azurras.configs;

import dev.christopherbell.azurras.models.Image;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "image-properties")
public class ImageProperties {
    private List<Image> images;
}
