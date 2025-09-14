package dev.christopherbell.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for API utilities.
 *
 * <p>Bound from application configuration using prefix {@code api.utils}.</p>
 */
@Configuration
@ConfigurationProperties(prefix = "api.utils")
@Data
public class ApiUtilProperties {
  private String saltPassword;
}
