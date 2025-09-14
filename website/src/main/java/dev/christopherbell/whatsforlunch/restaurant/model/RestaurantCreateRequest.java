package dev.christopherbell.whatsforlunch.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request to create a new restaurant.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RestaurantCreateRequest {
  private Address address;
  private String name;
  private String phoneNumber;
  private String website;
}
