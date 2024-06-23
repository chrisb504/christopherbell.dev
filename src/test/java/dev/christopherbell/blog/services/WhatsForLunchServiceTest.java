package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.properties.WhatsForLunchProperties;
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
}
