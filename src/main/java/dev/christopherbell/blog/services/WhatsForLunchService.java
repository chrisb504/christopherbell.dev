package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.properties.WhatsForLunchProperties;
import dev.christopherbell.blog.models.whatsforlunch.Restaurant;
import dev.christopherbell.blog.models.whatsforlunch.WhatsForLunchResponse;
import dev.christopherbell.libs.common.api.exceptions.InvalidRequestException;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class WhatsForLunchService {

  private final WhatsForLunchProperties whatsForLunchProperties;
  private static Restaurant restaurantOfTheDay;

  /**
   * Gets the restaurant of the day.
   *
   * @return a WhatsForLunchResponse containing the restaurant of the day.
   */
  public WhatsForLunchResponse getRestaurantOfTheDay() {
    return WhatsForLunchResponse.builder()
        .restaurants(List.of(restaurantOfTheDay))
        .build();
  }

  /**
   * Gets a restaurant by a requested id.
   *
   * @param id of the requested restaurant.
   * @return WhatsForLunchResponse containing the requested restaurant.
   * @throws InvalidRequestException is id is null or empty, or if restaurant is not found.
   */
  public WhatsForLunchResponse getRestaurantById(String id) throws InvalidRequestException {
    if (Objects.isNull(id) || id.isBlank()) {
      throw new InvalidRequestException("Id cannot be null or blank.");
    }

    for (var restaurant : whatsForLunchProperties.getRestaurants()) {
      if (restaurant.getId().equals(Integer.parseInt(id))) {
        return WhatsForLunchResponse.builder()
            .restaurants(List.of(restaurant))
            .build();
      }
    }

    throw new InvalidRequestException("No restaurant found");
  }

  /**
   * Gets all existing restaurants.
   *
   * @return a WhatsForLunchResponse containing a list of all existing restaurants
   */
  public WhatsForLunchResponse getRestaurants() {
    return WhatsForLunchResponse.builder()
        .restaurants(whatsForLunchProperties.getRestaurants())
        .build();
  }
}