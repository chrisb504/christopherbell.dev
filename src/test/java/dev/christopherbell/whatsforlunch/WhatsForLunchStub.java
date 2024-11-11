package dev.christopherbell.whatsforlunch;

import dev.christopherbell.whatsforlunch.model.Restaurant;
import java.util.List;
import java.util.UUID;

public class WhatsForLunchStub {

  public static String RESTAURANT_ID = "fcf591e8-3ba6-4cce-9992-c99f12c52ea1";
  public static String RESTAURANT_NAME = "Chuy's";

  public static List<Restaurant> getRestaurantsStub() {
    return List.of(getRestaurantStub(), getRestaurantStub());
  }

  public static Restaurant getRestaurantStub() {
    return Restaurant.builder()
        .id(UUID.fromString(RESTAURANT_ID))
        .name(RESTAURANT_NAME)
        .build();
  }
}
