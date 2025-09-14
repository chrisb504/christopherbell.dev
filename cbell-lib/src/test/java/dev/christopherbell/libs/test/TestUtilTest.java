package dev.christopherbell.libs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUtilTest {

  /**
   * Simple record representing the restaurant request used in tests.
   */
  record RestaurantRequest(
      String name,
      String phoneNumber,
      Address address,
      String website
  ) {}

  /**
   * Simple record representing an address within the test request.
   */
  record Address(
      String street1,
      String city,
      String state,
      String country,
      String postalCode
  ) {}

  @Test
  @DisplayName("Should deserialize RestaurantRequest from JSON")
  public void shouldDeserializeRestaurantRequest() {
    var request = TestUtil.readJsonAsObject(
        "/request/create-restaurant-request.json",
        RestaurantRequest.class
    );

    assertNotNull(request);
    assertEquals(TestUtilStub.RESTAURANT_NAME, request.name());
    assertEquals(TestUtilStub.RESTAURANT_PHONE_NUMBER, request.phoneNumber());
    assertEquals(TestUtilStub.RESTAURANT_CITY, request.address().city());
    assertEquals(TestUtilStub.RESTAURANT_COUNTRY, request.address().country());
    assertEquals(TestUtilStub.RESTAURANT_STATE, request.address().state());
    assertEquals(TestUtilStub.RESTAURANT_STREET_1, request.address().street1());
    assertEquals(TestUtilStub.RESTAURANT_POSTAL_CODE, request.address().postalCode());
    assertEquals(TestUtilStub.RESTAURANT_WEBSITE, request.website());
  }
}
