package dev.christopherbell.whatsforlunch;

import dev.christopherbell.libs.common.api.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class WhatsForLunchServiceTest {

  private WhatsForLunchService whatsForLunchService;
  @Autowired
  private WhatsForLunchProperties whatsForLunchProperties;

  @BeforeEach
  public void init() {
    whatsForLunchService = new WhatsForLunchService(whatsForLunchProperties);
  }

  @Test
  public void testSetRestaurantOfTheDay_success() {
    whatsForLunchService.setRestaurantOfTheDay();

    Assertions.assertNotNull(WhatsForLunchService.restaurantOfTheDay);
    Assertions.assertFalse(WhatsForLunchService.restaurantOfTheDay.getName().isBlank());
  }

  @Test
  public void testGetRestaurantOfTheDayTest_success() {
    var restaurant = whatsForLunchService.getRestaurantOfTheDay();

    Assertions.assertNotNull(WhatsForLunchService.restaurantOfTheDay);
    Assertions.assertFalse(WhatsForLunchService.restaurantOfTheDay.getName().isBlank());
    Assertions.assertEquals(restaurant.getRestaurants().getFirst().getName(),
        WhatsForLunchService.restaurantOfTheDay.getName());
  }

  @Test
  public void testGetRestaurantById_success() throws InvalidRequestException {
    var id = 0;

    var restaurant = whatsForLunchService.getRestaurantById(String.valueOf(id));
    Assertions.assertEquals(restaurant.getRestaurants().getFirst().getName(),
        whatsForLunchProperties.getRestaurants().getFirst().getName());
  }

  @Test
  public void testGetRestaurantById_failure_nullId() {
    Assertions.assertThrows(InvalidRequestException.class, () -> {
      whatsForLunchService.getRestaurantById(null);
    });
  }

  @Test
  public void testGetRestaurantById_failure_blankId() {
    Assertions.assertThrows(InvalidRequestException.class, () -> {
      whatsForLunchService.getRestaurantById("");
    });
  }

  @Test
  public void testGetRestaurantById_failure_doesNotExist() {
    Assertions.assertThrows(InvalidRequestException.class, () -> {
      whatsForLunchService.getRestaurantById("-1");
    });
  }

  @Test
  public void testGetRestaurants_success() {
    var restaurants = whatsForLunchService.getRestaurants();

    Assertions.assertEquals(restaurants.getRestaurants().size(), whatsForLunchProperties.getRestaurants().size());
  }
}
