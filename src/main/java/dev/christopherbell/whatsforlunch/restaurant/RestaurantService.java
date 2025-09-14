package dev.christopherbell.whatsforlunch.restaurant;

import com.mongodb.DuplicateKeyException;
import dev.christopherbell.libs.api.exception.InvalidRequestException;
import dev.christopherbell.libs.api.exception.ResourceExistsException;
import dev.christopherbell.libs.api.exception.ResourceNotFoundException;
import dev.christopherbell.whatsforlunch.restaurant.model.CreateRestaurantRequest;
import dev.christopherbell.whatsforlunch.restaurant.model.Restaurant;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantDetail;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantService {
  private final RestaurantMapper restaurantMapper;
  private final RestaurantRepository restaurantRepository;

  /**
   * Creates a new restaurant based on the provided request.
   *
   * @param request containing the details of the restaurant to be created.
   * @return a WhatsForLunchResponse containing the created restaurant details.
   * @throws Exception if there is an error during the creation process.
   */
  public RestaurantDetail createRestaurant(CreateRestaurantRequest request) throws Exception {
    var restaurant = restaurantMapper.toRestaurant(request);

    try {
      var savedRestaurant = restaurantRepository.save(restaurant);
      return restaurantMapper.toRestaurantDetail(savedRestaurant);
    } catch (DuplicateKeyException e) {
      throw new ResourceExistsException("Restaurant already exists", e);
    } catch (DataAccessException e) {
      throw new RuntimeException("Failed to save restaurant", e);
    }
  }

  /**
   * Gets all existing restaurants.
   *
   * @return a WhatsForLunchResponse containing a list of all existing restaurants
   */
  public List<RestaurantDetail> getRestaurants() {
    var restaurants = Optional.of(restaurantRepository.findAll())
        .orElseGet(List::of);
    return restaurants.stream()
        .map(restaurantMapper::toRestaurantDetail)
        .toList();
  }


  /**
   * Gets a restaurant by a requested id.
   *
   * @param id of the requested restaurant.
   * @return WhatsForLunchResponse containing the requested restaurant.
   * @throws InvalidRequestException is id is null or empty, or if restaurant is not found.
   */
  public RestaurantDetail getRestaurantById(
      String id
  ) throws InvalidRequestException, ResourceNotFoundException {
    if (id == null || id.isBlank()) {
      throw new InvalidRequestException("Restaurant id cannot be null or blank.");
    }

    return restaurantRepository.findById(id)
        .map(restaurantMapper::toRestaurantDetail)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found: " + id));
  }

  /**
   * This is a nightly job that will select a random restaurant per day.
   */
  @Scheduled(cron = "@midnight")
  public void setRestaurantOfTheDay() {
  }

  /**
   * Generates a random number based on the size of the total number of restaurants passed in.
   *
   * @param restaurants that we store in our application.yml file
   * @return a random number between 0 and total number of restaurants in our list.
   */
  private int getRandomInt(List<Restaurant> restaurants) {
    var random = new Random();
    return random.nextInt(restaurants.size());
  }
}
