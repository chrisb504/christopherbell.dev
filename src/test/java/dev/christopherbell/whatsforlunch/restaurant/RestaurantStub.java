package dev.christopherbell.whatsforlunch.restaurant;

import dev.christopherbell.whatsforlunch.restaurant.model.Address;
import dev.christopherbell.whatsforlunch.restaurant.model.RestaurantDetail;

public class RestaurantStub {
  public static final String ID = "632d6450-1c80-4e2a-b329-b6b5de486414";
  public static final String NAME = "Pflugerville Taco House";
  public static final String STREET_1 = "104 N Railroad Ave";
  public static final String CITY = "Pflugerville";
  public static final String STATE = "TX";
  public static final String COUNTRY = "USA";
  public static final String POSTAL_CODE = "78660";
  public static final String PHONE_NUMBER = "512-969-5012";
  public static final String WEBSITE = "https://www.pflugervilletacohouse.com";

  public static RestaurantDetail getRestaurantDetailStub(Address address) {
    return RestaurantDetail.builder()
        .id(ID)
        .name(NAME)
        .address(address)
        .phoneNumber(PHONE_NUMBER)
        .website(WEBSITE)
        .build();
  }

  public static Address getAddressStub() {
    return Address.builder()
        .street1(STREET_1)
        .city(CITY)
        .state(STATE)
        .country(COUNTRY)
        .postalCode(POSTAL_CODE)
        .build();
  }
}
