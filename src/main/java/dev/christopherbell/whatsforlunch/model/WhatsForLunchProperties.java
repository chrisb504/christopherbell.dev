package dev.christopherbell.whatsforlunch.model;

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
