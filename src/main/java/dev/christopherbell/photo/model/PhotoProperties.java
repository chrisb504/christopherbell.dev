package dev.christopherbell.photo.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Builder
@Data
@Configuration
@ConfigurationProperties(prefix = "photo-properties")
public class PhotoProperties {

  private List<Photo> photos;
}
