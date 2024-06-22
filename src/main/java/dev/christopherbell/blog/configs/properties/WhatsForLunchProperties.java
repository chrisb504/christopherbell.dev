package dev.christopherbell.blog.configs.properties;

import dev.christopherbell.blog.models.whatsforlunch.Restaurant;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wfl-properties")
public class WhatsForLunchProperties {

  private final List<Restaurant> restaurants;
}
