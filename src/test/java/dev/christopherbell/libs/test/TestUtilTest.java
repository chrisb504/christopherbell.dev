package dev.christopherbell.libs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.christopherbell.whatsforlunch.restaurant.model.CreateRestaurantRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUtilTest {

  @Test
  @DisplayName("Should deserialize CreateRestaurantRequest from JSON")
  public void shouldDeserializeRestaurantRequest() {
    var request = TestUtil.readJsonAsObject(
        "/request/create-restaurant-request.json",
        CreateRestaurantRequest.class
    );

    assertNotNull(request);
    assertEquals(request.getName(), TestUtilStub.RESTAURANT_NAME);
    assertEquals(request.getPhoneNumber(), TestUtilStub.RESTAURANT_PHONE_NUMBER);
    assertEquals(request.getAddress().getCity(), TestUtilStub.RESTAURANT_CITY);
    assertEquals(request.getAddress().getCountry(), TestUtilStub.RESTAURANT_COUNTRY);
    assertEquals(request.getAddress().getState(), TestUtilStub.RESTAURANT_STATE);
    assertEquals(request.getAddress().getStreet1(), TestUtilStub.RESTAURANT_STREET_1);
    assertEquals(request.getAddress().getPostalCode(), TestUtilStub.RESTAURANT_POSTAL_CODE);
    assertEquals(request.getWebsite(), TestUtilStub.RESTAURANT_WEBSITE);
  }
}
