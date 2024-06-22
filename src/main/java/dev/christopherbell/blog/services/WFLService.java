package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.Constants;
import dev.christopherbell.blog.configs.properties.WFLProperties;
import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.wfl.Restaurant;
import dev.christopherbell.blog.models.wfl.WFLResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WFLService {

  private final WFLProperties wflProperties;

  public WFLService(WFLProperties wflProperties) {
    this.wflProperties = wflProperties;
  }

  public WFLResponse getRestaurantOfTheDay() {
    return WFLResponse.builder()
        .restaurant(Restaurant.builder().build())
        .build();
  }

  public WFLResponse getRestaurantById(String id) {
    if (Objects.isNull(id) || id.isBlank()) {
      final var message = new Message("WFLService.getRestaurants.InvalidId", "Given id is blank, empty or null");
      log.error(message.getDescription());
      final var messages = List.of(message);
      return WFLResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    final var restaurants = this.wflProperties.getRestaurants();
    if (Objects.isNull(restaurants)) {
      final var message = new Message("WFLService.getRestaurants.NoResults",
          "No Restaurants found in the config file.");
      log.error(message.getDescription());
      final var messages = Arrays.asList(message);
      return WFLResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    Restaurant restaurant = null;
    for (Restaurant rest : restaurants) {
      if (rest.getId().equals(Integer.parseInt(id))) {
        restaurant = rest;
      }
    }
    return WFLResponse.builder()
        .restaurant(restaurant)
        .status(Constants.STATUS_SUCCESS)
        .build();
  }

  public WFLResponse getRestaurants() {
    final var restaurants = this.wflProperties.getRestaurants();
    if (Objects.isNull(restaurants)) {
      final var message = new Message("WFLService.getRestaurants.NoResults",
          "No Restaurants found in the config file.");
      log.error(message.getDescription());
      final var messages = List.of(message);
      return WFLResponse.builder()
          .messages(messages)
          .status(Constants.STATUS_FAILURE)
          .build();
    }
    return WFLResponse.builder()
        .restaurants(restaurants)
        .status(Constants.STATUS_SUCCESS)
        .build();
  }
}