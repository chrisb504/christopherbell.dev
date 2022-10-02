package dev.christopherbell.website.configs.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import dev.christopherbell.website.models.wfl.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wfl-properties")
public class WFLProperties {
    List<Restaurant> restaurants;
}
