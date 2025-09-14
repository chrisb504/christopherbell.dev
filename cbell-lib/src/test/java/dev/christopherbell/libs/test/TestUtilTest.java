package dev.christopherbell.libs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUtilTest {

  /** Simple record representing the restaurant request used in tests. */
  record RestaurantRequest(
      String name,
      String phoneNumber,
      Address address,
      String website
  ) {}

  /** Simple record representing an address within the test request. */
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
    assertEquals(request.name(), TestUtilStub.RESTAURANT_NAME);
    assertEquals(request.phoneNumber(), TestUtilStub.RESTAURANT_PHONE_NUMBER);
    assertEquals(request.address().city(), TestUtilStub.RESTAURANT_CITY);
    assertEquals(request.address().country(), TestUtilStub.RESTAURANT_COUNTRY);
    assertEquals(request.address().state(), TestUtilStub.RESTAURANT_STATE);
    assertEquals(request.address().street1(), TestUtilStub.RESTAURANT_STREET_1);
    assertEquals(request.address().postalCode(), TestUtilStub.RESTAURANT_POSTAL_CODE);
    assertEquals(request.website(), TestUtilStub.RESTAURANT_WEBSITE);
  }
}
